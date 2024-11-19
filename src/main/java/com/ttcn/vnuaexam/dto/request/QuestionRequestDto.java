package com.ttcn.vnuaexam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequestDto {
    private Long id;
    private String code;
    private Long subjectId;
    private Date createDate;
    private String createdBy;
    private Date modifyDate;
    private String modifiedBy;
    private String content;
    private String image;
    List<AnswerRequestDto> answers;
}
