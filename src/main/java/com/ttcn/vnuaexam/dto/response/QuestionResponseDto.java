package com.ttcn.vnuaexam.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class QuestionResponseDto {
    private String id;
    private String chapterId;
    private Date createDate;
    private String createdBy;
    private Date modifyDate;
    private String modifiedBy;
    private String content;
    private String image;
    private String type;

}
