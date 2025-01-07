package com.ttcn.vnuaexam.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum StatusExamRoomEnum {
    COMING_SON(1, "Sắp diễn ra" ),
    DOING(2, "Đang diễn ra. "),
    DONE(3, "Kết thúc. "),
    ;

    private final Integer code;
    private final String value;

    public static StatusExamRoomEnum fromCode(Integer code) {
        for (StatusExamRoomEnum statusRoom : StatusExamRoomEnum.values()) {
            if (Objects.equals(statusRoom.getCode(), code)) {
                return statusRoom;
            }
        }
        return null;
    }

    public static Map<Integer, String> getStatusRoom() {
        return Arrays.stream(StatusExamRoomEnum.values())
                .collect(Collectors.toMap(
                        StatusExamRoomEnum::getCode,
                        StatusExamRoomEnum::getValue
                ));
    }
}
