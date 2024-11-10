package com.ttcn.vnuaexam.service.mapper;

import com.ttcn.vnuaexam.dto.request.SubjectRequestDto;
import com.ttcn.vnuaexam.dto.response.SubjectResponseDto;
import com.ttcn.vnuaexam.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    @Mapping(target = "id", ignore = true)
    Subject requestDtoToEntity(SubjectRequestDto subjectRequestDto);

    @Mapping(source = "id", target = "id")
    SubjectResponseDto entityToDto(Subject subject);

    default String map(UUID value) {
        return value != null ? value.toString() : null;
    }

    default UUID map(String value) {
        return value != null ? UUID.fromString(value) : null;
    }
}
