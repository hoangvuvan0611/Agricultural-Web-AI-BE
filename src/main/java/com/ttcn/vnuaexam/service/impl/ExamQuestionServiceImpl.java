package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.dto.request.ExamQuestionRequestDto;
import com.ttcn.vnuaexam.dto.request.ExamRequestDto;
import com.ttcn.vnuaexam.dto.response.ExamQuestionResponseDto;
import com.ttcn.vnuaexam.entity.ExamQuestion;
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

    @Override
    public ExamQuestionResponseDto entityToResponseDto(ExamQuestion exam) {
        return examQuestionMapper.entityToResponseDto(exam);
    }

    @Override
    public ExamQuestion requestDtoToEntity(ExamQuestionRequestDto examQuestionRequestDto) {
        ExamQuestion examQuestion = examQuestionMapper.requestDtoToEntity(examQuestionRequestDto);
        examQuestionRepository.save(examQuestion);

        return examQuestion;
    }
}
