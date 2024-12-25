package com.ttcn.vnuaexam.service;

import com.ttcn.vnuaexam.dto.request.AnswerRequestDto;
import com.ttcn.vnuaexam.dto.response.AnswerResponseDto;
import com.ttcn.vnuaexam.entity.Answer;
import com.ttcn.vnuaexam.exception.EMException;

import java.util.List;

public interface AnswerService {
    AnswerResponseDto create(AnswerRequestDto requestDto) throws EMException;

    Boolean deleteById(Long id) throws EMException;

    List<AnswerResponseDto> getByQuestionId(Long questionId);

    AnswerResponseDto update(Long id, AnswerRequestDto requestDto) throws EMException;
}
