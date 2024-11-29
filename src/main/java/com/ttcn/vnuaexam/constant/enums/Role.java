package com.ttcn.vnuaexam.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role    {
    ADMIN(1,"Admin"),
    TEACHER(2,"Giảng viên" ),
    PROCTOR(3,"Giám thị" ),
    STUDENT(4,"Thí sinh" ),
    UNKNOWN_ROLE(0," un");
    ;
    @Getter
    private final Integer numRole;
    private final String value;


    public static Role formNumRole(Integer numRole) {
        for (Role role : Role.values()) {
            if (role.getNumRole() == numRole) {
                return role;
            }
        }
        return UNKNOWN_ROLE;
    }

}