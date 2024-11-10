package com.ttcn.vnuaexam.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamResultResponseDto {
    private String id;
    private String examSessionId;
    private double totalScore;
    private int correctCount;
    private int wrongCount;
    private int unAnswerCount;
}
