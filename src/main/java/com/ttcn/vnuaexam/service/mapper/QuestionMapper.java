package com.ttcn.vnuaexam.service.mapper;

import com.ttcn.vnuaexam.dto.request.QuestionRequestDto;
import com.ttcn.vnuaexam.dto.response.QuestionResponseDto;
import com.ttcn.vnuaexam.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    @Mapping(target = "id", ignore = true)
    Question requestToEntity(QuestionRequestDto questionRequestDto);

    QuestionResponseDto entityToResponse(Question question);

    @Mapping(target = "id", ignore = true)
    void setValue(QuestionRequestDto questionRequestDto,@MappingTarget Question question);
}
