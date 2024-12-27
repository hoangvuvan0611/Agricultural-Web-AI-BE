package com.ttcn.vnuaexam.service;

import com.ttcn.vnuaexam.dto.request.ExamSessionRequestDto;
import com.ttcn.vnuaexam.dto.response.ExamSessionResponseDto;
import com.ttcn.vnuaexam.exception.EMException;

import java.util.List;

public interface ExamSessionService {
    ExamSessionResponseDto add(ExamSessionRequestDto examSessionRequestDto);

    ExamSessionResponseDto update(Long id, ExamSessionRequestDto examSessionRequestDto) throws EMException;

    boolean delete(Long id) throws EMException;

    ExamSessionResponseDto findById(Long id) throws EMException;

    List<ExamSessionResponseDto> findAll();
}
