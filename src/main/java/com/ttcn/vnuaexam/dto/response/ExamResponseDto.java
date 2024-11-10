package com.ttcn.vnuaexam.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class ExamResponseDto {
    private String id;
    private String subjectId;
    private String createdBy;
    private String title;
    private String description;
    private int duration;
    private int totalQuestions;
    private Boolean isActive;
    private Date createDate;
    private String modifiedBy;
    private Date modifyDate;
    private Date examDate;
}
