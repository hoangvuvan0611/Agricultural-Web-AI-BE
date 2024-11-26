package com.ttcn.vnuaexam.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AnswerResponseDto {
    private Long id;
    private String questionId;
    private String code;
    private String content;
    private Boolean isCorrect;
}
