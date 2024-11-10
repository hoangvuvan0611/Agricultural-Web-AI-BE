package com.ttcn.vnuaexam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExamRequestDto {
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
