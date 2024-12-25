package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.constant.enums.ErrorCodeEnum;
import com.ttcn.vnuaexam.dto.request.UserSubjectRequestDto;
import com.ttcn.vnuaexam.dto.response.UserSubjectResponseDto;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.repository.*;
import com.ttcn.vnuaexam.service.UserSubjectService;
import com.ttcn.vnuaexam.service.mapper.UserMapper;
import com.ttcn.vnuaexam.service.mapper.UserSubjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserSubjectServiceImpl implements UserSubjectService {
    private final UserSubjectRepository userSubjectRepository;
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;
    private final UserSubjectMapper userSubjectMapper;

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN', 'TEACHER')")
    public UserSubjectResponseDto create(UserSubjectRequestDto requestDto) throws EMException {
        if (!subjectRepository.existsById(requestDto.getSubjectId())) {
            throw new EMException(ErrorCodeEnum.NOT_FOUND_SUBJECT);
        }

        if (!userRepository.existsById(requestDto.getUserId())) {
            throw new EMException(ErrorCodeEnum.NOT_FOUND_USER);
        }

        var entity = userSubjectMapper.requestToEntity(requestDto);
        userSubjectRepository.save(entity);
        return userSubjectMapper.entityToResponse(entity);
    }
}
