package com.ttcn.vnuaexam.service;

import com.ttcn.vnuaexam.dto.request.SemesterRequestDto;
import com.ttcn.vnuaexam.dto.response.SemesterResponseDto;
import com.ttcn.vnuaexam.dto.search.SearchDto;
import com.ttcn.vnuaexam.exception.EMException;
import org.springframework.data.domain.Page;

public interface SemesterService {
    SemesterResponseDto getById(Long id) throws EMException;

    SemesterResponseDto create(SemesterRequestDto requestDto);

    SemesterResponseDto update(Long id, SemesterRequestDto requestDto) throws EMException;

    Page<SemesterResponseDto> search(SearchDto searchDto);
}
