package com.ttcn.vnuaexam.service.mapper;

import com.ttcn.vnuaexam.dto.request.ExamRequestDto;
import com.ttcn.vnuaexam.dto.response.ExamResponseDto;
import com.ttcn.vnuaexam.entity.Exam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ExamMapper {
    @Mapping(target = "id", ignore = true)
    Exam requestToEntity(ExamRequestDto examRequestDto);

    ExamResponseDto entityToResponse(Exam exam);

    @Mapping(target = "id", ignore = true)
    void setValue(ExamRequestDto examRequestDto,@MappingTarget Exam exam);
}
