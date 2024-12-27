package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.constant.enums.ErrorCodeEnum;
import com.ttcn.vnuaexam.dto.request.AnswerRequestDto;
import com.ttcn.vnuaexam.dto.request.SemesterRequestDto;
import com.ttcn.vnuaexam.dto.response.AnswerResponseDto;
import com.ttcn.vnuaexam.dto.response.SemesterResponseDto;
import com.ttcn.vnuaexam.entity.Answer;
import com.ttcn.vnuaexam.entity.Semester;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.helper.DateHelper;
import com.ttcn.vnuaexam.repository.AnswerRepository;
import com.ttcn.vnuaexam.repository.QuestionRepository;
import com.ttcn.vnuaexam.repository.SemesterRepository;
import com.ttcn.vnuaexam.service.AnswerService;
import com.ttcn.vnuaexam.service.SemesterService;
import com.ttcn.vnuaexam.service.mapper.AnswerMapper;
import com.ttcn.vnuaexam.service.mapper.SemesterMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class SemesterServiceImpl implements SemesterService {
    private final SemesterRepository semesterRepository;
    private final SemesterMapper semesterMapper;

    @Override
    public SemesterResponseDto getById(Long id) throws EMException {
        Semester semester = semesterRepository.findById(id).orElseThrow(() -> new EMException(ErrorCodeEnum.NOT_FOUND));
        return semesterMapper.entityToResponse(semester);
    }

    @Override
    public SemesterResponseDto create(SemesterRequestDto requestDto) {
        var entity = semesterRepository.save(semesterMapper.requestToEntity(requestDto));
        return semesterMapper.entityToResponse(entity);
    }

    @Override
    public SemesterResponseDto update(Long id, SemesterRequestDto requestDto) throws EMException {
        var semester = semesterRepository.findById(id).orElseThrow(() -> new EMException(ErrorCodeEnum.NOT_FOUND));
        semesterMapper.setValue(requestDto, semester);
        semesterRepository.save(semester);
        return semesterMapper.entityToResponse(semester);
    }
}
