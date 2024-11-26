package com.ttcn.vnuaexam.dto.response;

import com.ttcn.vnuaexam.entity.BaseEntity;
import com.ttcn.vnuaexam.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto extends BaseEntity {
    private Long id;
    private String username;
    private String password;
    private String code;
    private String fullName;
    private String role;
    private Boolean isActive;

}
