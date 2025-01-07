package com.ttcn.vnuaexam.service;

import com.ttcn.vnuaexam.dto.client.UserClientDto;
import com.ttcn.vnuaexam.dto.request.UserRequestDto;
import com.ttcn.vnuaexam.dto.response.UserResponseDto;
import com.ttcn.vnuaexam.dto.search.SearchDto;
import com.ttcn.vnuaexam.dto.search.UserSearchDto;
import com.ttcn.vnuaexam.entity.User;
import com.ttcn.vnuaexam.exception.EMException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    UserResponseDto addUser(UserRequestDto userRequestDto) throws EMException;

    UserResponseDto updateUser(Long id,UserRequestDto userRequestDto) throws EMException;

    UserResponseDto getUserById(Long id) throws EMException;

    boolean deleteUser(Long id) throws EMException;

    List<UserResponseDto> getAllUsers() throws EMException;

    String importListStudent(List<UserRequestDto> requestDtoList);

    UserClientDto getCurrentUser() throws EMException;

    Page<UserResponseDto> searchStudent(UserSearchDto dto);

    List<UserResponseDto> searchUserList(UserSearchDto dto);
}
