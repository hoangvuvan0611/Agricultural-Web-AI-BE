package com.ttcn.vnuaexam.service.mapper;

import com.ttcn.vnuaexam.dto.request.UserRequestDto;
import com.ttcn.vnuaexam.dto.response.UserResponseDto;
import com.ttcn.vnuaexam.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserMapper {
//    @Mapping(target = "id", ignore = true)
//    User requestToEntity(UserRequestDto requestDto);
//
//    @Mapping(source = "id", target = "id")
//    UserResponseDto entityToResponse(User user);
//
//    default String map(UUID value) {
//        return value != null ? value.toString() : null;
//    }
//
//    default UUID map(String value) {
//        return value != null ? UUID.fromString(value) : null;
//    }
}
