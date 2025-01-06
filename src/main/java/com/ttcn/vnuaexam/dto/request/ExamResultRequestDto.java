package com.ttcn.vnuaexam.dto.request;

import com.ttcn.vnuaexam.dto.BaseObjectDto;
import com.ttcn.vnuaexam.dto.response.AnswerResponseDto;
import com.ttcn.vnuaexam.entity.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExamResultRequestDto extends BaseObjectDto {
    private Long id;
    private Long examId;
//    private Long examRoomId;
    private Long studentId;
    private List<AnswerResponseDto> answerList;
    private double totalScore;
    private int correctCount;
    private int wrongCount;
    private int unAnswerCount;
}
