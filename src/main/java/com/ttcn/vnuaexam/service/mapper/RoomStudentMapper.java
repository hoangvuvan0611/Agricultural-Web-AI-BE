package com.ttcn.vnuaexam.service.mapper;

import com.ttcn.vnuaexam.dto.request.RoomStudentRequestDto;
import com.ttcn.vnuaexam.dto.response.RoomStudentResponseDto;
import com.ttcn.vnuaexam.entity.RoomStudent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoomStudentMapper {
    @Mapping(target = "id", ignore = true)
    RoomStudent requestDtoToEntity(RoomStudentRequestDto requestDto);

    RoomStudentResponseDto entityToResponseDto(RoomStudent entity);

    @Mapping(target = "id", ignore = true)
    void setValue(RoomStudentRequestDto requestDto, @MappingTarget RoomStudent entity);
}
