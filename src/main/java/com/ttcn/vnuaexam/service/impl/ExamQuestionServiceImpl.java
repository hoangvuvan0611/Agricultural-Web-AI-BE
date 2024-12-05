package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.repository.ExamQuestionRepository;
import com.ttcn.vnuaexam.service.ExamQuestionService;
import com.ttcn.vnuaexam.service.mapper.ExamQuestionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExamQuestionServiceImpl implements ExamQuestionService {
    private final ExamQuestionMapper examQuestionMapper;
    private final ExamQuestionRepository examQuestionRepository;

}
