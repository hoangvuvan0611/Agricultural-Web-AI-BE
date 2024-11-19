package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.dto.request.ExamSessionRequestDto;
import com.ttcn.vnuaexam.dto.response.ExamSessionResponseDto;
import com.ttcn.vnuaexam.entity.ExamSession;
import com.ttcn.vnuaexam.repository.ExamSessionRepository;
import com.ttcn.vnuaexam.service.ExamSessionService;
import com.ttcn.vnuaexam.service.mapper.ExamSessionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExamSessionServiceImpl implements ExamSessionService {
    private final ExamSessionMapper examSessionMapper;
    private final ExamSessionRepository examSessionRepository;

}
