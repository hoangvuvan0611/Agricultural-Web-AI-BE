package com.ttcn.vnuaexam.service;

import com.ttcn.vnuaexam.dto.request.ExamSessionRequestDto;
import com.ttcn.vnuaexam.dto.response.ExamSessionResponseDto;
import com.ttcn.vnuaexam.entity.ExamSession;

public interface ExamSessionService {
    ExamSession requestDtoToExamSession(ExamSessionRequestDto examSessionRequestDto);

    ExamSessionResponseDto examSessionToResponseDto(ExamSession examSession);

}
