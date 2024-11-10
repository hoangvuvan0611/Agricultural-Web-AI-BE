package com.ttcn.vnuaexam.service.mapper;

import com.ttcn.vnuaexam.dto.request.ExamRequestDto;
import com.ttcn.vnuaexam.dto.response.ExamResponseDto;
import com.ttcn.vnuaexam.entity.Exam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ExamMapper {
    @Mapping(target = "id", ignore = true)
    Exam RequestDtoToEntity(ExamRequestDto examRequestDto);

    @Mapping(source = "id", target = "id")
    ExamResponseDto EntityToResponseDto(Exam exam);

    default String map(UUID value) {
        return value != null ? value.toString() : null;
    }

    default UUID map(String value) {
        return value != null ? UUID.fromString(value) : null;
    }

}
