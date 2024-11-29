package com.ttcn.vnuaexam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FileDescriptionRequestDto {
    private String id;
    private Date createDate;
    private String createBy;
    private String modifiedBy;
    private Date modifyDate;
    private int contentSize;
    private String contentType;
    private String name;

}
