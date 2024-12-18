package com.ttcn.vnuaexam.service;

import com.ttcn.vnuaexam.dto.request.QuestionRequestDto;
import com.ttcn.vnuaexam.dto.response.QuestionResponseDto;
import com.ttcn.vnuaexam.dto.search.QuestionSearchDto;
import com.ttcn.vnuaexam.exception.EMException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface QuestionService {
    QuestionResponseDto getById(Long id) throws EMException;

    List<QuestionResponseDto> getAllByIds(List<Long> ids) throws EMException;

    QuestionResponseDto create(QuestionRequestDto questionRequestDto) throws EMException;

    QuestionResponseDto update(Long id, QuestionRequestDto questionRequestDto) throws EMException;

    String deleteByIds(List<Long> ids);

    Page<QuestionResponseDto> search(QuestionSearchDto searchDto);
}
