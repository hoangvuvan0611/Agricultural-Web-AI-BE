package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.dto.request.ExamResultRequestDto;
import com.ttcn.vnuaexam.dto.response.ExamResultResponseDto;
import com.ttcn.vnuaexam.entity.ExamResult;
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

    @Override
    public ExamResult requestDtoToEntity(ExamResultRequestDto requestDto) {
        ExamResult examResult = examResultMapper.requestDtoToEntity(requestDto);
        examResultRepository.save(examResult);
        return examResult;
    }

    @Override
    public ExamResultResponseDto entityToResponseDto(ExamResult examResult) {
        return examResultMapper.entityToDto(examResult);
    }
}
