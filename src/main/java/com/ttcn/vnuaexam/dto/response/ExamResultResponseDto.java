package com.ttcn.vnuaexam.dto.response;

import com.ttcn.vnuaexam.dto.BaseObjectDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExamResultResponseDto extends BaseObjectDto {
    private Long id;
    private Long examId;
    private Long examRoomId;
    private Long studentId;
    private double totalScore;
    private int correctCount;
    private int wrongCount;
    private int unAnswerCount;
}
