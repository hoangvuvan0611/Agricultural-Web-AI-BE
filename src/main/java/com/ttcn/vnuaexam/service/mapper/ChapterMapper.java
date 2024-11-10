package com.ttcn.vnuaexam.service.mapper;

import com.ttcn.vnuaexam.dto.request.ChapterRequestDto;
import com.ttcn.vnuaexam.dto.response.ChapterResponseDto;
import com.ttcn.vnuaexam.entity.Chapter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ChapterMapper {
    @Mapping(target = "id", ignore = true)
    ChapterResponseDto entityToResponseDto(Chapter chapter);

    @Mapping(source = "id", target = "id")
    Chapter requestDtoToEntity(ChapterRequestDto requestDto);

    default String map(UUID value) {
        return value != null ? value.toString() : null;
    }

    default UUID map(String value) {
        return value != null ? UUID.fromString(value) : null;
    }
}
