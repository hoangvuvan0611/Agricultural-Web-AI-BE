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
    private Long id;
    private Long examRoomId;
    private Long studentId;
    private double totalScore;
    private int correctCount;
    private int wrongCount;
    private int unAnswerCount;
}
