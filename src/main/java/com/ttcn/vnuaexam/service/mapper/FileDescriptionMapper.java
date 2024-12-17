package com.ttcn.vnuaexam.service.mapper;

import com.ttcn.vnuaexam.dto.request.FileDescriptionRequestDto;
import com.ttcn.vnuaexam.dto.response.FileDescriptionResponseDto;
import com.ttcn.vnuaexam.entity.FileDescription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FileDescriptionMapper {
    @Mapping(target = "id", ignore = true)
    FileDescription requestToEntity(FileDescriptionRequestDto requestDto);

    FileDescriptionResponseDto entityToResponse(FileDescription entity);

    @Mapping(target = "id", ignore = true)
    void setValue(FileDescriptionRequestDto requestDto,@MappingTarget FileDescription entity);
}
