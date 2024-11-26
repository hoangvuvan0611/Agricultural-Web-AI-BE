package com.ttcn.vnuaexam.constant.enums;

import lombok.Getter;

@Getter
public enum Role    {
    ADMIN(1),
    TEACHER(2),
    PROCTOR(3),
    STUDENT(4),
    ;
    private final int numRole;

    Role(int numRole) {
        this.numRole = numRole;
    }

    public static Role formRole(int numRole) {
        for (Role role : Role.values()) {
            if (role.numRole == numRole){
                return role;
            }
        }
        return null;
    }

}
