package com.ttcn.vnuaexam.response;

import com.ttcn.vnuaexam.constant.enums.ErrorCodeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EMResponse<T> {
    private int code;
    private String message;
    private T data;
    private long total;

    public EMResponse(T data) {
        this.data = data;
        this.code = 200;
    }

    public EMResponse(int code, String message, T data, long total) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.total = total;
    }

    public EMResponse(ErrorCodeEnum errorCode) {
        this.code = errorCode.getErrorCode();
        this.message = errorCode.getMessage();
    }

    public EMResponse(T data, ErrorCodeEnum errorCode) {
        this.data = data;
        this.code = errorCode.getErrorCode();
        this.message = errorCode.getMessage();
    }

    public EMResponse(T data, long total) {
        this.data = data;
        this.code = 200;
        this.total = total;
    }

    public EMResponse(int code, T data, long total) {
        this.code = code;
        this.data = data;
        this.total = total;
    }

    public EMResponse(String message) {
        this.message = message;
    }

    public EMResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
