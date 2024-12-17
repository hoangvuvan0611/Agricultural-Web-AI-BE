package com.ttcn.vnuaexam.rest;

import com.nimbusds.jose.JOSEException;
import com.ttcn.vnuaexam.authentication.AuthenticationRequest;
import com.ttcn.vnuaexam.authentication.AuthenticationResponse;
import com.ttcn.vnuaexam.authentication.IntrospectRequest;
import com.ttcn.vnuaexam.authentication.IntrospectResponse;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.response.EMResponse;
import com.ttcn.vnuaexam.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class RestAuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public EMResponse<AuthenticationResponse> logIn(@RequestBody AuthenticationRequest authenticationRequest) throws EMException {
        var result = authenticationService.login(authenticationRequest);
        return new EMResponse<>(result);
    }

    @PostMapping("/introspect")
    public EMResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) throws EMException, ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return new EMResponse<>(result);
    }
}
