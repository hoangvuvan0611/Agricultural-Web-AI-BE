package com.ttcn.vnuaexam.service.impl;


import com.ttcn.vnuaexam.constant.enums.ErrorCodeEnum;
import com.ttcn.vnuaexam.dto.request.ExamSetRequestDto;
import com.ttcn.vnuaexam.dto.response.ExamSetResponseDto;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.repository.ExamSetRepository;
import com.ttcn.vnuaexam.service.ExamSetService;
import com.ttcn.vnuaexam.service.mapper.ExamSetMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ExamSetServiceImpl implements ExamSetService {
    private final ExamSetRepository examSetRepository;
    private final ExamSetMapper examSetMapper;

    @Override
    public ExamSetResponseDto getById(Long id) throws EMException {
        var examSetEntity = examSetRepository.findById(id)
                .orElseThrow(() -> new EMException(ErrorCodeEnum.NOT_FOUND));
        return examSetMapper.entityToResponse(examSetEntity);
    }

    @Override
    public ExamSetResponseDto create(ExamSetRequestDto requestDto) {
        var entity = examSetMapper.requestToEntity(requestDto);
        return examSetMapper.entityToResponse(examSetRepository.save(entity));
    }

    @Override
    public ExamSetResponseDto update(Long id, ExamSetRequestDto requestDto) throws EMException {
        var entity = examSetRepository.findById(id)
                .orElseThrow(() -> new EMException(ErrorCodeEnum.NOT_FOUND));
        examSetMapper.setValue(requestDto, entity);
        return examSetMapper.entityToResponse(examSetRepository.save(entity));
    }


}