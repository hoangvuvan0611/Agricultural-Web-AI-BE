package com.ttcn.vnuaexam.service.mapper;

import com.ttcn.vnuaexam.dto.request.FileDescriptionRequestDto;
import com.ttcn.vnuaexam.dto.response.FileDescriptionResponseDto;
import com.ttcn.vnuaexam.entity.FileDescription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface FileDescriptionMapper {
    @Mapping(target = "id", ignore = true)
    FileDescription requestDtoToFileDescription(FileDescriptionRequestDto fileDescriptionRequestDto);

    @Mapping(source = "id", target = "id")
    FileDescriptionResponseDto entityToDto(FileDescription fileDescription);

    default String map(UUID value) {
        return value != null ? value.toString() : null;
    }

    default UUID map(String value) {
        return value != null ? UUID.fromString(value) : null;
    }
}
