package com.ttcn.vnuaexam.service.mapper;

import com.ttcn.vnuaexam.dto.request.StudentAnswersRequestDto;
import com.ttcn.vnuaexam.dto.response.StudentAnswersResponseDto;
import com.ttcn.vnuaexam.entity.StudentAnswers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface StudentAnswersMapper {

    @Mapping(target = "id", ignore = true)
    StudentAnswers requestDtoToEntity(StudentAnswersRequestDto requestDto);

    @Mapping(source = "id", target = "id")
    StudentAnswersResponseDto entityToResponseDto(StudentAnswers studentAnswers);

    default String map(UUID value) {
        return value != null ? value.toString() : null;
    }

    default UUID map(String value) {
        return value != null ? UUID.fromString(value) : null;
    }
}
