package com.ttcn.vnuaexam.service.mapper;

import com.ttcn.vnuaexam.dto.request.DepartmentRequestDto;
import com.ttcn.vnuaexam.dto.response.DepartmentResponseDto;
import com.ttcn.vnuaexam.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    @Mapping(target = "id", ignore = true)
    Department requestToEntity(DepartmentRequestDto requestDto);

    DepartmentResponseDto entityToResponse(Department department);

    @Mapping(target = "id", ignore = true)
    void setValue(DepartmentRequestDto dto, @MappingTarget Department entity);
}
