package com.ttcn.vnuaexam.service;

import com.ttcn.vnuaexam.dto.request.ExamRequestDto;
import com.ttcn.vnuaexam.dto.response.ExamResponseDto;
import com.ttcn.vnuaexam.entity.Exam;

public interface ExamService {
    ExamResponseDto examToResponseDto(Exam exam);

    Exam requestDtoToExam(ExamRequestDto examRequestDto);
}
