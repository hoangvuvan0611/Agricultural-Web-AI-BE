package com.ttcn.vnuaexam.service.mapper;

import com.ttcn.vnuaexam.dto.request.ExamQuestionRequestDto;
import com.ttcn.vnuaexam.dto.request.ExamRequestDto;
import com.ttcn.vnuaexam.dto.response.ExamQuestionResponseDto;
import com.ttcn.vnuaexam.dto.response.ExamResponseDto;
import com.ttcn.vnuaexam.entity.Exam;
import com.ttcn.vnuaexam.entity.ExamQuestion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ExamQuestionMapper {
//    @Mapping(target = "id", ignore = true)
//    ExamQuestion requestToEntity(ExamQuestionRequestDto requestDto);

    ExamQuestionResponseDto entityToResponse(ExamQuestion examQuestion);

    @Mapping(target = "id", ignore = true)
    void setValue(ExamQuestionRequestDto requestDto,@MappingTarget ExamQuestion examQuestion);
}
