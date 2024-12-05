package com.ttcn.vnuaexam.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusExamEnum {
    DOING(1, "Đang tạo"),
    DONE(2, "Hoàn thành"),
    ;

    private final int code;
    private final String value;
}
