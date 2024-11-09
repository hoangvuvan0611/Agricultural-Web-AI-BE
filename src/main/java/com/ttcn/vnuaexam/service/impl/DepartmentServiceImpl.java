package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.dto.response.DepartmentResponseDto;
import com.ttcn.vnuaexam.entity.Department;
import com.ttcn.vnuaexam.repository.DepartmentRepository;
import com.ttcn.vnuaexam.service.DepartmentService;
import com.ttcn.vnuaexam.service.mapper.DepartmentResponseMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentResponseMapper departmentResponseMapper;

    @Override
    public DepartmentResponseDto getById(UUID id) {
        Department department = departmentRepository.findById(id).orElse(null);
        return departmentResponseMapper.entityToResponse(department);
    }

}
