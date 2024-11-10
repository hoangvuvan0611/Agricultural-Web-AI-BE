package com.ttcn.vnuaexam.service;

import com.ttcn.vnuaexam.dto.request.FileDescriptionRequestDto;
import com.ttcn.vnuaexam.dto.response.FileDescriptionResponseDto;
import com.ttcn.vnuaexam.entity.FileDescription;

public interface FileDescriptionService {
    FileDescriptionResponseDto entityToDto(FileDescription fileDescription);

    FileDescription requestDtoToFileDescription(FileDescriptionRequestDto fileDescriptionRequestDto);
}
