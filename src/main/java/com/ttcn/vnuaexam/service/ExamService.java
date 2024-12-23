package com.ttcn.vnuaexam.service;

import com.ttcn.vnuaexam.constant.enums.ErrorCodeEnum;
import com.ttcn.vnuaexam.dto.request.ExamRequestDto;
import com.ttcn.vnuaexam.dto.response.ExamResponseDto;
import com.ttcn.vnuaexam.dto.search.ExamSearchDto;
import com.ttcn.vnuaexam.entity.Exam;
import com.ttcn.vnuaexam.exception.EMException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ExamService {
    ExamResponseDto getById(Long id) throws EMException;

    ExamResponseDto create(ExamRequestDto examRequestDto) throws EMException;

    ExamResponseDto update(ExamRequestDto examRequestDto, Long examId) throws EMException;

    ErrorCodeEnum saveQuestion(Long examId, List<Long> questionIds) throws EMException;

    Page<ExamResponseDto> search(ExamSearchDto searchDto);

    Boolean deleteById(Long id) throws EMException;
}
