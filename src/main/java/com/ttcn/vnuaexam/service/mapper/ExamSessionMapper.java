package com.ttcn.vnuaexam.service.mapper;

import com.ttcn.vnuaexam.dto.request.ExamSessionRequestDto;
import com.ttcn.vnuaexam.dto.response.ExamSessionResponseDto;
import com.ttcn.vnuaexam.entity.ExamSession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ExamSessionMapper {
    @Mapping(target = "id", ignore = true)
    ExamSession requestDtoToEntity(ExamSessionRequestDto examSessionRequestDto);

    @Mapping(source = "id", target = "id")
    ExamSessionResponseDto entityToResponseDto(ExamSession examSession);
    default String map(UUID value) {
        return value != null ? value.toString() : null;
    }

    default UUID map(String value) {
        return value != null ? UUID.fromString(value) : null;
    }
}
