package com.ttcn.vnuaexam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExamResultRequestDto {
    private String id;
    private String examSessionId;
    private double totalScore;
    private int correctCount;
    private int wrongCount;
    private int unAnswerCount;

}
