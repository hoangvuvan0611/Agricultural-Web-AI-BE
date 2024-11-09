package com.ttcn.vnuaexam.service.mapper;

import com.ttcn.vnuaexam.dto.request.DepartmentRequestDto;
import com.ttcn.vnuaexam.dto.response.DepartmentResponseDto;
import com.ttcn.vnuaexam.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DepartmentResponseMapper {
    @Mapping(source = "id", target = "id", ignore = true)
    Department requestToEntity(DepartmentRequestDto requestDto);

    DepartmentResponseDto entityToResponse(Department department);
}
