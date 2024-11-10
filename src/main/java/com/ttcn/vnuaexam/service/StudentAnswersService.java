package com.ttcn.vnuaexam.service;

import com.ttcn.vnuaexam.dto.request.StudentAnswersRequestDto;
import com.ttcn.vnuaexam.dto.response.StudentAnswersResponseDto;
import com.ttcn.vnuaexam.entity.StudentAnswers;

public interface StudentAnswersService {
    StudentAnswers requestDtoToEntity(StudentAnswersRequestDto requestDto);

    StudentAnswersResponseDto entityToResponseDto(StudentAnswers studentAnswers);
}
