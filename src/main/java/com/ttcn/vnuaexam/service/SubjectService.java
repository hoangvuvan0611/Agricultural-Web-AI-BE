package com.ttcn.vnuaexam.service;

import com.ttcn.vnuaexam.dto.request.SubjectRequestDto;
import com.ttcn.vnuaexam.dto.response.SubjectResponseDto;
import com.ttcn.vnuaexam.entity.Subject;

public interface SubjectService {
    Subject requestDtoToEntity(SubjectRequestDto requestDto);

    SubjectResponseDto entityToResponseDto(Subject subject);
}
