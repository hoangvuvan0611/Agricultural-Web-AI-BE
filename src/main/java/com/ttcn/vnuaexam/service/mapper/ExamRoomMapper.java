package com.ttcn.vnuaexam.service.mapper;

import com.ttcn.vnuaexam.dto.request.ExamRoomRequestDto;
import com.ttcn.vnuaexam.dto.response.ExamRoomResponseDto;
import com.ttcn.vnuaexam.entity.ExamRoom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ExamRoomMapper {
    @Mapping(target = "id", ignore = true)
    ExamRoom requestDtoToEntity(ExamRoomRequestDto examRoomRequestDto);

    ExamRoomResponseDto entityToResponseDto(ExamRoom examRoom);

    @Mapping(target = "id", ignore = true)
    void setValue(ExamRoomRequestDto examRoomRequestDto, @MappingTarget ExamRoom examRoom);
}
