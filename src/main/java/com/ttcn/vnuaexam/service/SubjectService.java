package com.ttcn.vnuaexam.service;

import com.ttcn.vnuaexam.dto.request.DepartmentRequestDto;
import com.ttcn.vnuaexam.dto.request.SubjectRequestDto;
import com.ttcn.vnuaexam.dto.response.DepartmentResponseDto;
import com.ttcn.vnuaexam.dto.response.SubjectResponseDto;
import com.ttcn.vnuaexam.dto.search.DepartmentSearchDto;
import com.ttcn.vnuaexam.dto.search.SubjectSearchDto;
import com.ttcn.vnuaexam.entity.Subject;
import com.ttcn.vnuaexam.exception.EMException;
import org.springframework.data.domain.Page;

public interface SubjectService {
    SubjectResponseDto getById(Long id) throws EMException;

    SubjectResponseDto create(SubjectRequestDto requestDto) throws EMException;

    SubjectResponseDto update(SubjectRequestDto requestDto, Long id) throws EMException;

    Boolean deleteById(Long id) throws EMException;

    Page<SubjectResponseDto> searchSubject(SubjectSearchDto searchDto);
}
