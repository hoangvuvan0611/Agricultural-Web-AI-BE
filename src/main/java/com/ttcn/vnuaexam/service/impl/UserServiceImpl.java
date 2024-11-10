package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.dto.request.UserRequestDto;
import com.ttcn.vnuaexam.dto.response.UserResponseDto;
import com.ttcn.vnuaexam.entity.User;
import com.ttcn.vnuaexam.repository.UserRepository;
import com.ttcn.vnuaexam.service.UserService;
import com.ttcn.vnuaexam.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User requestDtoToEntity(UserRequestDto userRequestDto) {
        User user = userMapper.requestToEntity(userRequestDto);
        userRepository.save(user);
        return user;
    }

    @Override
    public UserResponseDto entityToResponseDto(User user) {
        return userMapper.entityToResponse(user);
    }
}
