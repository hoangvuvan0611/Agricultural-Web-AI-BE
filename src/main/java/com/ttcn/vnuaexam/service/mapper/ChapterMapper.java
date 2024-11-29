package com.ttcn.vnuaexam.service.mapper;

import com.ttcn.vnuaexam.dto.request.ChapterRequestDto;
import com.ttcn.vnuaexam.dto.response.ChapterResponseDto;
import com.ttcn.vnuaexam.entity.Chapter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ChapterMapper {
    @Mapping(target = "id", ignore = true)
    Chapter requestToEntity(ChapterRequestDto requestDto);

    ChapterResponseDto entityToResponse(Chapter chapter);

    @Mapping(target = "id", ignore = true)
    void setValue(ChapterRequestDto dto, @MappingTarget Chapter entity);
}
