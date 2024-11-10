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
public class AnswerRequestDto {
    private String id;
    private String questionId;
    private String content;
    private Boolean isCorrect;
    private Date createDate;
    private String createdBy;
    private Date modifyDate;
    private String modifiedBy;
}
