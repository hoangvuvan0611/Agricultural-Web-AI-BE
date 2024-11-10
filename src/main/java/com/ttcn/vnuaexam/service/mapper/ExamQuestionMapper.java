package com.ttcn.vnuaexam.service.mapper;

import com.ttcn.vnuaexam.dto.request.ExamQuestionRequestDto;
import com.ttcn.vnuaexam.dto.response.ExamQuestionResponseDto;
import com.ttcn.vnuaexam.entity.ExamQuestion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ExamQuestionMapper {
    @Mapping(target = "id", ignore = true)
    ExamQuestion requestDtoToEntity(ExamQuestionRequestDto examQuestionRequestDto);

    @Mapping(source = "id", target = "id")
    ExamQuestionResponseDto entityToResponseDto(ExamQuestion examQuestion);

    default String map(UUID value) {
        return value != null ? value.toString() : null;
    }

    default UUID map(String value) {
        return value != null ? UUID.fromString(value) : null;
    }
}
