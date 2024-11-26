package com.ttcn.vnuaexam.service.mapper;

import com.ttcn.vnuaexam.constant.enums.Role;
import com.ttcn.vnuaexam.dto.request.UserRequestDto;
import com.ttcn.vnuaexam.dto.response.UserResponseDto;
import com.ttcn.vnuaexam.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "role", target = "role")
    User requestToEntity(UserRequestDto requestDto);

    @Mapping(source = "role", target = "role", qualifiedByName = "roleToString")
    UserResponseDto entityToResponse(User user);

    @Mapping(target = "id", ignore = true)
    void setValue(UserRequestDto requestDto,@MappingTarget User user);

    @org.mapstruct.Named("roleToString")
    default String roleToString(int role) {
        return Role.formRole(role).name();
    }
}
