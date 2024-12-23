package com.ttcn.vnuaexam.dto.client;

import com.ttcn.vnuaexam.dto.response.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserClientDto {
    private UserResponseDto user;
    private Boolean isRoleAdmin = false;
    private Boolean isRoleTeacher = false;
    private Boolean isRoleProctor = false;
    private Boolean isRoleStudent = false;
}
