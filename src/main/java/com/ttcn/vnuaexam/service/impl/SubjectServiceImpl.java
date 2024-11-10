package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.dto.request.SubjectRequestDto;
import com.ttcn.vnuaexam.dto.response.SubjectResponseDto;
import com.ttcn.vnuaexam.entity.Subject;
import com.ttcn.vnuaexam.repository.SubjectRepository;
import com.ttcn.vnuaexam.service.SubjectService;
import com.ttcn.vnuaexam.service.mapper.SubjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SubjectServiceImpl implements SubjectService {

    private SubjectMapper subjectMapper;
    private SubjectRepository subjectRepository;

    @Override
    public Subject requestDtoToEntity(SubjectRequestDto requestDto) {
        Subject subject = subjectMapper.requestDtoToEntity(requestDto);
        subjectRepository.save(subject);
        return subject;
    }

    @Override
    public SubjectResponseDto entityToResponseDto(Subject subject) {
        return subjectMapper.entityToDto(subject);
    }
}
