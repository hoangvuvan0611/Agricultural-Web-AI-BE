package com.ttcn.vnuaexam.service.mapper;

import com.ttcn.vnuaexam.dto.request.DepartmentRequestDto;
import com.ttcn.vnuaexam.dto.response.DepartmentResponseDto;
import com.ttcn.vnuaexam.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    @Mapping(target = "id", ignore = true)
    Department requestToEntity(DepartmentRequestDto requestDto);

    @Mapping(source = "id", target = "id")
    DepartmentResponseDto entityToResponse(Department department);

    default String map(UUID value) {
        return value != null ? value.toString() : null;
    }

    default UUID map(String value) {
        return value != null ? UUID.fromString(value) : null;
    }
}
