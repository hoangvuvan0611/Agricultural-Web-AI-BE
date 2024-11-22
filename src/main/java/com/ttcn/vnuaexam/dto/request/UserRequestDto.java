package com.ttcn.vnuaexam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    private Long id;
    private String username;
    private String password;
    private String code;
    private String fullName;
    private int role;
    private String createdBy;
    private Date createDate;
    private String modifiedBy;
    private Date modifyDate;
    private Boolean isActive;

}
