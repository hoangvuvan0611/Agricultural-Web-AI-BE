package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.constant.enums.ErrorCodeEnum;
import com.ttcn.vnuaexam.dto.request.ExamSessionRequestDto;
import com.ttcn.vnuaexam.dto.response.ExamSessionResponseDto;
import com.ttcn.vnuaexam.entity.ExamSession;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.repository.ExamSessionRepository;
import com.ttcn.vnuaexam.service.ExamSessionService;
import com.ttcn.vnuaexam.service.mapper.ExamSessionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExamSessionServiceImpl implements ExamSessionService {

    private final ExamSessionMapper examSessionMapper;
    private final ExamSessionRepository examSessionRepository;

    @Override
    public ExamSessionResponseDto add(ExamSessionRequestDto examSessionRequestDto) {
        var examSession = examSessionMapper.requestDtoToEntity(examSessionRequestDto);
        examSessionRepository.save(examSession);
        return examSessionMapper.entityToResponseDto(examSession);
    }

    @Override
    public ExamSessionResponseDto update(Long id, ExamSessionRequestDto examSessionRequestDto) throws EMException {
        var ExamSession = examSessionRepository.findById(id)
                .orElseThrow(() -> new EMException(ErrorCodeEnum.NOT_FOUND_EXAMSESSION));
        examSessionMapper.setValue(examSessionRequestDto, ExamSession);
        examSessionRepository.save(ExamSession);
        return examSessionMapper.entityToResponseDto(ExamSession);
    }

    @Override
    public boolean delete(Long id) throws EMException {
        var ExamSession = examSessionRepository.findById(id)
                .orElseThrow(()-> new EMException(ErrorCodeEnum.NOT_FOUND_EXAMSESSION));
        examSessionRepository.delete(ExamSession);
        return true;
    }

    @Override
    public ExamSessionResponseDto findById(Long id) throws EMException {
        var examSession = examSessionRepository.findById(id).orElseThrow(() -> new EMException(ErrorCodeEnum.NOT_FOUND_EXAMSESSION));
        return examSessionMapper.entityToResponseDto(examSession);
    }

    @Override
    public List<ExamSessionResponseDto> findAll() {
        List<ExamSession> examSessions = examSessionRepository.findAll();
        return examSessions.stream()
                .map(examSessionMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }





}
