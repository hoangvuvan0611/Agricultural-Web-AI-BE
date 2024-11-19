package com.ttcn.vnuaexam.service;

import com.ttcn.vnuaexam.dto.request.DepartmentRequestDto;
import com.ttcn.vnuaexam.dto.response.DepartmentResponseDto;
import com.ttcn.vnuaexam.dto.search.DepartmentSearchDto;
import com.ttcn.vnuaexam.exception.EMException;
import org.springframework.data.domain.Page;

public interface DepartmentService {
    DepartmentResponseDto getById(Long id) throws EMException;

    DepartmentResponseDto create(DepartmentRequestDto requestDto) throws EMException;

    DepartmentResponseDto update(DepartmentRequestDto requestDto, Long id) throws EMException;

    Boolean deleteById(Long id) throws EMException;

    Page<DepartmentResponseDto> searchDepartment(DepartmentSearchDto searchDto);
}
