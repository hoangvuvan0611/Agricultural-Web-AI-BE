package com.ttcn.vnuaexam.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AnswerResponseDto {
    private String id;
    private String questionId;
    private String content;
    private Boolean isCorrect;
    private Date createDate;
    private String createdBy;
    private Date modifyDate;
    private String modifiedBy;
}
