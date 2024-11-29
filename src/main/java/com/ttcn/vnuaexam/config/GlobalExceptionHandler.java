package com.ttcn.vnuaexam.config;

import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.response.EMResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Hỗ trợ trả EMException ra response
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EMException.class)
    public EMResponse<Void> handleEMException(EMException e) {
        return new EMResponse<>(e.getCode(), e.getMessage(), null, 0);
    }
}
