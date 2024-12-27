package com.ttcn.vnuaexam.service.mapper;

import com.ttcn.vnuaexam.dto.request.AnswerRequestDto;
import com.ttcn.vnuaexam.dto.request.SemesterRequestDto;
import com.ttcn.vnuaexam.dto.response.AnswerResponseDto;
import com.ttcn.vnuaexam.dto.response.SemesterResponseDto;
import com.ttcn.vnuaexam.entity.Answer;
import com.ttcn.vnuaexam.entity.Semester;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SemesterMapper {
    @Mapping(target = "id", ignore = true)
    Semester requestToEntity(SemesterRequestDto requestDto);

    SemesterResponseDto entityToResponse(Semester semester);

    @Mapping(target = "id", ignore = true)
    void setValue(SemesterRequestDto requestDto, @MappingTarget Semester semester);
}
