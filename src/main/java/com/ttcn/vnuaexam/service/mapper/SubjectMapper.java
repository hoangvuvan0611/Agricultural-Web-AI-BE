package com.ttcn.vnuaexam.service.mapper;

import com.ttcn.vnuaexam.dto.request.SubjectRequestDto;
import com.ttcn.vnuaexam.dto.response.SubjectResponseDto;
import com.ttcn.vnuaexam.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    @Mapping(target = "id", ignore = true)
    Subject requestToEntity(SubjectRequestDto requestDto);

    SubjectResponseDto entityToResponse(Subject subject);

    @Mapping(target = "id", ignore = true)
    void setValue(SubjectRequestDto dto, @MappingTarget Subject entity);
}
