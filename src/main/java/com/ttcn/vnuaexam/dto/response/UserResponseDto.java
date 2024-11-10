package com.ttcn.vnuaexam.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserResponseDto {
    private String id;
    private String username;
    private String password;
    private String code;
    private String fullName;
    private int role;
    private String createdBy;
    private Date createDate;
    private String modifiedBy;
    private Date modifiedDate;
    private Boolean isActive;

}
