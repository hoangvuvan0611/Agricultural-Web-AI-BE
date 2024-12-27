package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.repository.ExamResultRepository;
import com.ttcn.vnuaexam.service.ExamResultService;
import com.ttcn.vnuaexam.service.mapper.ExamResultMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExamResultServiceImpl implements ExamResultService {
    private final ExamResultRepository examResultRepository;
    private final ExamResultMapper examResultMapper;
}
