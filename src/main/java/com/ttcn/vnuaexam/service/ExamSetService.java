package com.ttcn.vnuaexam.service;

import com.ttcn.vnuaexam.dto.request.ExamSetRequestDto;
import com.ttcn.vnuaexam.dto.response.ExamSetResponseDto;
import com.ttcn.vnuaexam.exception.EMException;

public interface ExamSetService {
    ExamSetResponseDto getById(Long id) throws EMException;

    ExamSetResponseDto create(ExamSetRequestDto examSetRequestDto);

    ExamSetResponseDto update(Long id, ExamSetRequestDto requestDto) throws EMException;
}
