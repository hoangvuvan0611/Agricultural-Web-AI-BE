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
    private Long id;
    private Long questionId;
    private String code;
    private String content;
    private Boolean isCorrect;
}
