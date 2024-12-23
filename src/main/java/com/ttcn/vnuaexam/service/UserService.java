package com.ttcn.vnuaexam.service;

import com.ttcn.vnuaexam.dto.client.UserClientDto;
import com.ttcn.vnuaexam.dto.request.UserRequestDto;
import com.ttcn.vnuaexam.dto.response.UserResponseDto;
import com.ttcn.vnuaexam.exception.EMException;

import java.util.List;

public interface UserService {
    UserResponseDto addUser(UserRequestDto userRequestDto) throws EMException;

    UserResponseDto updateUser(Long id,UserRequestDto userRequestDto) throws EMException;

    UserResponseDto getUserById(Long id) throws EMException;

    boolean deleteUser(Long id) throws EMException;

    List<UserResponseDto> getAllUsers() throws EMException;

    String importListStudent(List<UserRequestDto> requestDtoList);

    UserClientDto getCurrentUser() throws EMException;
}
