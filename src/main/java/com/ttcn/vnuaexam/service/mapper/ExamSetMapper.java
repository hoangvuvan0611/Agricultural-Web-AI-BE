package com.ttcn.vnuaexam.service.mapper;

import com.ttcn.vnuaexam.dto.request.ExamRequestDto;
import com.ttcn.vnuaexam.dto.request.ExamSetRequestDto;
import com.ttcn.vnuaexam.dto.response.ExamResponseDto;
import com.ttcn.vnuaexam.dto.response.ExamSetResponseDto;
import com.ttcn.vnuaexam.entity.Exam;
import com.ttcn.vnuaexam.entity.ExamSet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ExamSetMapper {
    @Mapping(target = "id", ignore = true)
    ExamSet requestToEntity(ExamSetRequestDto requestDto);

    ExamSetResponseDto entityToResponse(ExamSet examSet);

    @Mapping(target = "id", ignore = true)
    void setValue(ExamSetRequestDto requestDto, @MappingTarget ExamSet examSet);
}
