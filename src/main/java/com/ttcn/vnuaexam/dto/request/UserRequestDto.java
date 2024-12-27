package com.ttcn.vnuaexam.dto.request;

import com.ttcn.vnuaexam.helper.DateHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private String dob;
    private String classCode;
    private String address;
    private Integer role;
    private String createdBy;
    private Date createDate;
    private String modifiedBy;
    private Date modifyDate;
    private Boolean isActive;

    public LocalDate getDob() {
        if (dob != null)
            return DateHelper.fromDateSlash(dob);
        return null;
    }
}
