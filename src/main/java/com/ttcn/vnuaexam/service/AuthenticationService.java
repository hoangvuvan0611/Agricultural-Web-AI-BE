package com.ttcn.vnuaexam.service;

import com.nimbusds.jose.JOSEException;
import com.ttcn.vnuaexam.authentication.AuthenticationRequest;
import com.ttcn.vnuaexam.authentication.AuthenticationResponse;
import com.ttcn.vnuaexam.authentication.IntrospectRequest;
import com.ttcn.vnuaexam.authentication.IntrospectResponse;
import com.ttcn.vnuaexam.exception.EMException;

import java.text.ParseException;

public interface AuthenticationService {
    AuthenticationResponse login(AuthenticationRequest request) throws EMException;

    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;


}
