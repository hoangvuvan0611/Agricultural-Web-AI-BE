package com.ttcn.vnuaexam.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum Role    {
    ADMIN(1,"Admin"),
    TEACHER(2,"Giảng viên" ),
    PROCTOR(3,"Giám thị" ),
    STUDENT(4,"Thí sinh" ),
    ;
    @Getter
    private final Integer numRole;
    private final String value;

    public static Role formNumRole(Integer numRole) {
        for (Role role : Role.values()) {
            if (Objects.equals(role.getNumRole(), numRole)) {
                return role;
            }
        }
        return null;
    }

    public static Map<Integer, String> getRoleMap() {
        return Arrays.stream(Role.values())
                .collect(Collectors.toMap(
                        Role::getNumRole,
                        Role::getValue
                ));
    }
}