package com.ttcn.vnuaexam.service.mapper;

import com.ttcn.vnuaexam.dto.request.ExamResultRequestDto;
import com.ttcn.vnuaexam.dto.response.ExamResultResponseDto;
import com.ttcn.vnuaexam.entity.ExamResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ExamResultMapper {
    @Mapping(target = "id", ignore = true)
    ExamResult requestDtoToEntity(ExamResultRequestDto requestDto);

    @Mapping(source = "id", target = "id")
    ExamResultResponseDto entityToDto(ExamResult examResult);

    default String map(UUID value) {
        return value != null ? value.toString() : null;
    }

    default UUID map(String value) {
        return value != null ? UUID.fromString(value) : null;
    }

}
