package com.ttcn.vnuaexam.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeAnswerEnum {
    LAST_ANSWER(1, "Đáp án cuối cùng"  ),
    FIRST_ANSWER(2, "Đáp án đầu tiên"),
    ;

    private final int code;
    private final String value;
}
