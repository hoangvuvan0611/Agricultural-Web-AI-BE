package com.ttcn.vnuaexam.service.mapper;

import com.ttcn.vnuaexam.constant.enums.Role;
import com.ttcn.vnuaexam.dto.request.UserRequestDto;
import com.ttcn.vnuaexam.dto.response.UserResponseDto;
import com.ttcn.vnuaexam.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

//    @Mapping(target = "id", ignore = true)
//    @Mapping(source = "role", target = "role")
    User requestToEntity(UserRequestDto requestDto);

    @Mapping(source = "role", target = "role")
    UserResponseDto entityToResponse(User user);

    @Mapping(target = "id", ignore = true)
    void setValue(UserRequestDto requestDto, @MappingTarget User user);

    default Role map(int value) {
        for (Role role : Role.values()) {
            if (role.getNumRole() == value) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role id: " + value);
    }
}
