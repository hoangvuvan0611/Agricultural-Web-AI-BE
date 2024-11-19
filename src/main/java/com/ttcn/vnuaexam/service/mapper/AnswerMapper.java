package com.ttcn.vnuaexam.service.mapper;

import com.ttcn.vnuaexam.dto.request.AnswerRequestDto;
import com.ttcn.vnuaexam.dto.response.AnswerResponseDto;
import com.ttcn.vnuaexam.entity.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    @Mapping(target = "id", ignore = true)
    Answer requestToEntity(AnswerRequestDto requestDto);

    AnswerResponseDto entityToResponse(Answer answer);

    @Mapping(target = "id", ignore = true)
    void setValue(AnswerResponseDto responseDto, @MappingTarget Answer answer);
}
