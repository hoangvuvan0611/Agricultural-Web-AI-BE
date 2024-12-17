package com.ttcn.vnuaexam.authentication;

import com.ttcn.vnuaexam.constant.enums.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationResponse {
    private String token;
    private Boolean authenticated;
    private String username;
    private String code;
    private String fullName;
    private Role role;

}
