package com.ttcn.vnuaexam.service;

import com.ttcn.vnuaexam.dto.request.UserRequestDto;
import com.ttcn.vnuaexam.dto.response.UserResponseDto;
import com.ttcn.vnuaexam.entity.User;

public interface UserService {
    User requestDtoToEntity(UserRequestDto userRequestDto);

    UserResponseDto entityToResponseDto(User user);
}
