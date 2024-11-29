package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.dto.request.FileDescriptionRequestDto;
import com.ttcn.vnuaexam.dto.response.FileDescriptionResponseDto;
import com.ttcn.vnuaexam.entity.FileDescription;
import com.ttcn.vnuaexam.repository.FileDescriptionRepository;
import com.ttcn.vnuaexam.service.FileDescriptionService;
import com.ttcn.vnuaexam.service.mapper.FileDescriptionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FileDescriptionServiceImpl implements FileDescriptionService {
    private final FileDescriptionRepository fileDescriptionRepository;
    private final FileDescriptionMapper fileDescriptionMapper;

}
