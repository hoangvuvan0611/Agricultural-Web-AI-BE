package com.ttcn.vnuaexam.dto.response;

import com.ttcn.vnuaexam.constant.enums.Role;
import com.ttcn.vnuaexam.entity.BaseEntity;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto extends BaseEntity {
    private Long id;
    private String username;
    private String code;
    private String fullName;
    private LocalDate dob;
    private String classCode;
    private String address;
    private Role role;
    private Boolean isActive;

    public String getRoleValue() {
        return role != null ? role.getValue() : null;
    }
}
