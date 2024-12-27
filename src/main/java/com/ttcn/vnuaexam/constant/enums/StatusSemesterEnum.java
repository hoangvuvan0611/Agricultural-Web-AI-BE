package com.ttcn.vnuaexam.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusSemesterEnum {
    COMING_SOON(0, "Sắp diễn ra"),
    DOING(1, "Đang dễn ra"),
    CLOSED(2, "Kết thúc"),
    ;

    private final int code;
    private final String value;

    public static StatusSemesterEnum fromCode(int code) {
        for (StatusSemesterEnum statusSemesterEnum : StatusSemesterEnum.values()) {
            if (statusSemesterEnum.getCode() == code) {
                return statusSemesterEnum;
            }
        }
        return null;
    }

    public static String codeToValue(int code) {
        var statusSemesterEnum = fromCode(code);
        if (statusSemesterEnum != null) {
            return statusSemesterEnum.getValue();
        }
        return null;
    }
}
