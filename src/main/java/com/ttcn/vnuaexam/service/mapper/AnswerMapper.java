package com.ttcn.vnuaexam.service.mapper;

import com.ttcn.vnuaexam.dto.request.AnswerRequestDto;
import com.ttcn.vnuaexam.dto.response.AnswerResponseDto;
import com.ttcn.vnuaexam.entity.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    @Mapping(target = "id", ignore = true)
    AnswerResponseDto entityToResponse(Answer answer);

    @Mapping(source = "id", target = "id")
    Answer requestToEntity(AnswerRequestDto answerRequestDto);

    default String map(UUID value) {
        return value != null ? value.toString() : null;
    }

    default UUID map(String value) {
        return value != null ? UUID.fromString(value) : null;
    }
}
