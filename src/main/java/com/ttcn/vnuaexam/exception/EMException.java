package com.ttcn.vnuaexam.exception;

import com.ttcn.vnuaexam.constant.enums.ErrorCodeEnum;
import lombok.Getter;

@Getter
public class EMException extends Exception {
    private final int code;
    private final String message;

    public EMException(ErrorCodeEnum errorEnum) {
        this.code = errorEnum.getErrorCode();
        this.message = errorEnum.getMessage();
    }

    public EMException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
