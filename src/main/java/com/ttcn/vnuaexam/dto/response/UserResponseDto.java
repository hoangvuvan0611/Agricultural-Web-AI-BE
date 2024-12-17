package com.ttcn.vnuaexam.dto.response;

import com.ttcn.vnuaexam.constant.enums.Role;
import com.ttcn.vnuaexam.entity.BaseEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto extends BaseEntity {
    private Long id;
    private String username;
    private String password;
    private String code;
    private String fullName;
    private Role role;
    private Boolean isActive;

    public String getRoleValue() {
        return role != null ? role.getValue() : null;
    }
}
