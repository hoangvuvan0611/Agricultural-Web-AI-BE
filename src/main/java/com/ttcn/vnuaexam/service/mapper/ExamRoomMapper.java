package com.ttcn.vnuaexam.service.mapper;

import com.ttcn.vnuaexam.dto.request.ExamSessionRequestDto;
import com.ttcn.vnuaexam.dto.response.ExamSessionResponseDto;
import com.ttcn.vnuaexam.entity.ExamSession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ExamSessionMapper {
    @Mapping(target = "id", ignore = true)
    ExamSession requestDtoToEntity(ExamSessionRequestDto examSessionRequestDto);

    ExamSessionResponseDto entityToResponseDto(ExamSession examSession);

    @Mapping(target = "id", ignore = true)
    void setValue(ExamSessionRequestDto examSessionRequestDto, @MappingTarget ExamSession examSession);
}
