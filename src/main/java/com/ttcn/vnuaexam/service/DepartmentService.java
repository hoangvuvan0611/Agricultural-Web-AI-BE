package com.ttcn.vnuaexam.service;

import com.ttcn.vnuaexam.dto.response.DepartmentResponseDto;

import java.util.UUID;

public interface DepartmentService {
    DepartmentResponseDto getById(UUID id);
}
