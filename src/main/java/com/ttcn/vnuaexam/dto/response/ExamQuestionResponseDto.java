package com.ttcn.vnuaexam.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamQuestionResponseDto {
    private String id;
    private String examId;
    private String questionId;
    private double score;
    private int orderNumber;
}
