package com.ttcn.vnuaexam.service.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.ttcn.vnuaexam.authentication.AuthenticationRequest;
import com.ttcn.vnuaexam.authentication.AuthenticationResponse;
import com.ttcn.vnuaexam.authentication.IntrospectRequest;
import com.ttcn.vnuaexam.authentication.IntrospectResponse;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.repository.UserRepository;
import com.ttcn.vnuaexam.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;

import static com.nimbusds.jose.JOSEObjectType.JWT;
import static com.ttcn.vnuaexam.constant.enums.ErrorCodeEnum.NOT_FOUND_USERNAME;
import static com.ttcn.vnuaexam.constant.enums.ErrorCodeEnum.UNAUTHENTICATED;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    @NonFinal
    @Value("${jwt.signer.key}")
    protected String SIGNER_KEY ;

    @Override
    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();

        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expirationDate = signedJWT.getJWTClaimsSet().getExpirationTime();
        var verified = signedJWT.verify(verifier);

        return IntrospectResponse.builder()
                .valid(verified && expirationDate.after(new Date()))
                .build();
    }


    @Override
    public AuthenticationResponse login(AuthenticationRequest request) throws EMException {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new EMException(NOT_FOUND_USERNAME));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authentication = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!authentication) {
            throw new EMException(UNAUTHENTICATED);
        }

        var token = generateToken(request.getUsername());

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    private String generateToken(String username) {

        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("Test.com")
                .issueTime(new Date())
                .expirationTime(new Date(new Date().getTime() + 3600 * 1000))
                .claim("User", "Custom")
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("JWT Sign Error", e);
            throw new RuntimeException(e);
        }
    }
}
