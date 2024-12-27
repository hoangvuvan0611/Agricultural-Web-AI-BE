package com.ttcn.vnuaexam.service;

import com.ttcn.vnuaexam.dto.request.SubjectRequestDto;
import com.ttcn.vnuaexam.dto.response.ChapterResponseDto;
import com.ttcn.vnuaexam.dto.response.SubjectResponseDto;
import com.ttcn.vnuaexam.dto.search.SearchDto;
import com.ttcn.vnuaexam.dto.search.SubjectSearchDto;
import com.ttcn.vnuaexam.exception.EMException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SubjectService {
    SubjectResponseDto getById(Long id) throws EMException;

    SubjectResponseDto create(SubjectRequestDto requestDto) throws EMException;

    SubjectResponseDto update(SubjectRequestDto requestDto, Long id) throws EMException;

    Boolean deleteById(Long id) throws EMException;

    Page<SubjectResponseDto> searchSubject(SubjectSearchDto dto) throws EMException;
}
