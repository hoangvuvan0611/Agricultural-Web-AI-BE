package com.ttcn.vnuaexam.dto.client;

import com.ttcn.vnuaexam.constant.enums.Role;
import com.ttcn.vnuaexam.dto.response.UserResponseDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserClientDto {
    private String username;
    private String code;
    private String fullName;
    private Role role;

}
