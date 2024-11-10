package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.dto.request.DepartmentRequestDto;
import com.ttcn.vnuaexam.dto.response.DepartmentResponseDto;
import com.ttcn.vnuaexam.entity.Department;
import com.ttcn.vnuaexam.repository.DepartmentRepository;
import com.ttcn.vnuaexam.service.DepartmentService;
import com.ttcn.vnuaexam.service.mapper.DepartmentMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public DepartmentResponseDto departmentToResponse(Department department) {
        return departmentMapper.entityToResponse(department);
    }

    @Override
    public Department requestToDepartment(DepartmentRequestDto requestDto) {
        Department department = departmentMapper.requestToEntity(requestDto);
        departmentRepository.save(department);
        return department;
    }


}
