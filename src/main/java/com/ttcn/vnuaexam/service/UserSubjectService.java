package com.ttcn.vnuaexam.service;

import com.ttcn.vnuaexam.dto.request.UserSubjectRequestDto;
import com.ttcn.vnuaexam.dto.response.UserSubjectResponseDto;
import com.ttcn.vnuaexam.exception.EMException;

public interface UserSubjectService {
    UserSubjectResponseDto create(UserSubjectRequestDto requestDto) throws EMException;
}
