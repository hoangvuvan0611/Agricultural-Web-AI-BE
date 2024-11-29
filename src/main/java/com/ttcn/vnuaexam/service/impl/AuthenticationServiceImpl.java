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
import com.ttcn.vnuaexam.constant.enums.Role;
import com.ttcn.vnuaexam.entity.User;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

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

        var token = generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    private String generateToken(User user) {

        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("Test.com")
                .issueTime(new Date())
                .expirationTime(new Date(new Date().getTime() + 3600 * 1000))
                .claim("Scope", buildScope(user))
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

    private String buildScope(User user) {
        StringJoiner scopeJoiner = new StringJoiner(" ");

        if (user.getRole() != null) {
            // Chuyển Integer role thành enum Role
            Role role = Role.formNumRole(user.getRole());

            // Thực hiện thao tác với enum Role mà không lấy thuộc tính value hay numRole
            // Ví dụ: bạn có thể dùng tên enum hoặc thêm logic xử lý tùy ý
            switch (role) {
                case ADMIN:
                    // Logic xử lý khi role là ADMIN
                    scopeJoiner.add(role.name()); // Lấy tên của enum, ví dụ "ADMIN"
                    break;
                case TEACHER:
                    // Logic xử lý khi role là TEACHER
                    scopeJoiner.add(role.name()); // Lấy tên của enum, ví dụ "TEACHER"
                    break;
                case PROCTOR:
                    // Logic xử lý khi role là PROCTOR
                    scopeJoiner.add(role.name()); // Lấy tên của enum, ví dụ "PROCTOR"
                    break;
                case STUDENT:
                    scopeJoiner.add(role.name());
                    break;
                default:
                    scopeJoiner.add("UNKNOWN_ROLE");
                    break;
            }
        } else {
            scopeJoiner.add("UNKNOWN_ROLE");
        }
        return scopeJoiner.toString();
    }
}
