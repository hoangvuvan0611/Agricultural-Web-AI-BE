package com.ttcn.vnuaexam.service;

import com.ttcn.vnuaexam.dto.request.AnswerRequestDto;
import com.ttcn.vnuaexam.dto.response.AnswerResponseDto;
import com.ttcn.vnuaexam.entity.Answer;

public interface AnswerService {
    AnswerResponseDto answerToResponse(Answer answer);

    Answer requestToAnswer(AnswerRequestDto requestDto);
}
