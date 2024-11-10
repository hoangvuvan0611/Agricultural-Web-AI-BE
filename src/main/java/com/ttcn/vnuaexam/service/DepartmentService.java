package com.ttcn.vnuaexam.service;

import com.ttcn.vnuaexam.dto.request.DepartmentRequestDto;
import com.ttcn.vnuaexam.dto.response.DepartmentResponseDto;
import com.ttcn.vnuaexam.entity.Department;

import java.util.UUID;

public interface DepartmentService {
    DepartmentResponseDto departmentToResponse(Department department);
    Department requestToDepartment(DepartmentRequestDto requestDto);
}
