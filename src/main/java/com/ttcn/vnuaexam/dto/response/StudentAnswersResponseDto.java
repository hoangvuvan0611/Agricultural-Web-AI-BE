package com.ttcn.vnuaexam.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StudentAnswersResponseDto {
    private String id;
    private String examSessionId;
    private String examQuestionId;
    private String answerId;
    private Date answerDate;
}
