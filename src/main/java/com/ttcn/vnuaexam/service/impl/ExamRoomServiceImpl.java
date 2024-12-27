package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.constant.enums.ErrorCodeEnum;
import com.ttcn.vnuaexam.dto.request.ExamRoomRequestDto;
import com.ttcn.vnuaexam.dto.response.ExamRoomResponseDto;
import com.ttcn.vnuaexam.entity.ExamRoom;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.repository.ExamRoomRepository;
import com.ttcn.vnuaexam.service.ExamRoomService;
import com.ttcn.vnuaexam.service.mapper.ExamRoomMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExamRoomServiceImpl implements ExamRoomService {

    private final ExamRoomMapper examSessionMapper;
    private final ExamRoomRepository examRoomRepository;

    @Override
    public ExamRoomResponseDto add(ExamRoomRequestDto ExamRoomRequestDto) {
        var examSession = examSessionMapper.requestDtoToEntity(ExamRoomRequestDto);
        examRoomRepository.save(examSession);
        return examSessionMapper.entityToResponseDto(examSession);
    }

    @Override
    public ExamRoomResponseDto update(Long id, ExamRoomRequestDto ExamRoomRequestDto) throws EMException {
        var ExamSession = examRoomRepository.findById(id)
                .orElseThrow(() -> new EMException(ErrorCodeEnum.NOT_FOUND_EXAMSESSION));
        examSessionMapper.setValue(ExamRoomRequestDto, ExamSession);
        examRoomRepository.save(ExamSession);
        return examSessionMapper.entityToResponseDto(ExamSession);
    }

    @Override
    public boolean delete(Long id) throws EMException {
        var ExamSession = examRoomRepository.findById(id)
                .orElseThrow(()-> new EMException(ErrorCodeEnum.NOT_FOUND_EXAMSESSION));
        examRoomRepository.delete(ExamSession);
        return true;
    }

    @Override
    public ExamRoomResponseDto findById(Long id) throws EMException {
        var examSession = examRoomRepository.findById(id).orElseThrow(() -> new EMException(ErrorCodeEnum.NOT_FOUND_EXAMSESSION));
        return examSessionMapper.entityToResponseDto(examSession);
    }

    @Override
    public List<ExamRoomResponseDto> findAll() {
        List<ExamRoom> examSessions = examRoomRepository.findAll();
        return examSessions.stream()
                .map(examSessionMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }





}
