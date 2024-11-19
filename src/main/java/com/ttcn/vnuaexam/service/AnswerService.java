package com.ttcn.vnuaexam.service;

import com.ttcn.vnuaexam.dto.request.AnswerRequestDto;
import com.ttcn.vnuaexam.dto.response.AnswerResponseDto;
import com.ttcn.vnuaexam.entity.Answer;
import com.ttcn.vnuaexam.exception.EMException;

public interface AnswerService {
    AnswerResponseDto create(AnswerRequestDto requestDto) throws EMException;
}
