package com.ttcn.vnuaexam.service.mapper;

import com.ttcn.vnuaexam.dto.request.ExamResultRequestDto;
import com.ttcn.vnuaexam.dto.request.ExamRoomRequestDto;
import com.ttcn.vnuaexam.dto.response.ExamResultResponseDto;
import com.ttcn.vnuaexam.entity.ExamResult;
import com.ttcn.vnuaexam.entity.ExamRoom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ExamResultMapper {
    @Mapping(target = "id", ignore = true)
    ExamResult requestToEntity(ExamResultRequestDto requestDto);

    @Mapping(source = "id", target = "id")
    ExamResultResponseDto entityToResponse(ExamResult examResult);

    @Mapping(target = "id", ignore = true)
    void setValue(ExamResultRequestDto requestDto, @MappingTarget ExamResult examResult);
}
