package com.ttcn.vnuaexam.service.mapper;

import com.ttcn.vnuaexam.dto.request.ExamQuestionRequestDto;
import com.ttcn.vnuaexam.dto.request.ExamRequestDto;
import com.ttcn.vnuaexam.dto.request.UserSubjectRequestDto;
import com.ttcn.vnuaexam.dto.response.ExamQuestionResponseDto;
import com.ttcn.vnuaexam.dto.response.UserSubjectResponseDto;
import com.ttcn.vnuaexam.entity.Exam;
import com.ttcn.vnuaexam.entity.ExamQuestion;
import com.ttcn.vnuaexam.entity.UserSubject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserSubjectMapper {
    @Mapping(target = "id", ignore = true)
    UserSubject requestToEntity(UserSubjectRequestDto requestDto);

    UserSubjectResponseDto entityToResponse(UserSubject userSubject);

    @Mapping(target = "id", ignore = true)
    void setValue(UserSubjectRequestDto requestDto, @MappingTarget UserSubject userSubject);
}
