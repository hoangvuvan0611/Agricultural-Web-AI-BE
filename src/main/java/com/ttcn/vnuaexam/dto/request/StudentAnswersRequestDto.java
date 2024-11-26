package com.ttcn.vnuaexam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentAnswersRequestDto {
    private String id;
    private String examSessionId;
    private String examQuestionId;
    private String answerId;
    private Date answerDate;

}
