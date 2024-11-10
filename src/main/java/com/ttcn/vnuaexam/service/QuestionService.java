package com.ttcn.vnuaexam.service;

import com.ttcn.vnuaexam.dto.request.QuestionRequestDto;
import com.ttcn.vnuaexam.dto.response.QuestionResponseDto;
import com.ttcn.vnuaexam.entity.Question;

public interface QuestionService {
    QuestionResponseDto questionToResponse(Question question);

    Question RequestToQuestion(QuestionRequestDto questionRequestDto);
}
