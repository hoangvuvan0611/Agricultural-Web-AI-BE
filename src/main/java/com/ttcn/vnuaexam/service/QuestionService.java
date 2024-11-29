package com.ttcn.vnuaexam.service;

import com.ttcn.vnuaexam.dto.request.QuestionRequestDto;
import com.ttcn.vnuaexam.dto.response.QuestionResponseDto;
import com.ttcn.vnuaexam.exception.EMException;

public interface QuestionService {
    QuestionResponseDto getById(Long id) throws EMException;

    QuestionResponseDto create(QuestionRequestDto questionRequestDto) throws EMException;

    QuestionResponseDto update(Long id, QuestionRequestDto questionRequestDto) throws EMException;

    Boolean deleteById(Long id) throws EMException;

//    Page<QuestionResponseDto> search(QuestionSearchDto searchDto);
}
