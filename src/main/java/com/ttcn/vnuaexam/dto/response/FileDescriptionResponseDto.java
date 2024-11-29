package com.ttcn.vnuaexam.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FileDescriptionResponseDto {
    private String id;
    private Date createDate;
    private String createBy;
    private String modifiedBy;
    private Date modifyDate;
    private int contentSize;
    private String contentType;
    private String name;
}
