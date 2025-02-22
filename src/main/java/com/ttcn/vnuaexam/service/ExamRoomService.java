package com.ttcn.vnuaexam.service;

import com.ttcn.vnuaexam.dto.request.ExamRoomRequestDto;
import com.ttcn.vnuaexam.dto.response.ExamRoomResponseDto;
import com.ttcn.vnuaexam.dto.search.ExamRoomSearchDto;
import com.ttcn.vnuaexam.dto.search.SearchDto;
import com.ttcn.vnuaexam.exception.EMException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ExamRoomService {
    ExamRoomResponseDto add(ExamRoomRequestDto examRoomRequestDto) throws EMException;

    ExamRoomResponseDto update(Long id, ExamRoomRequestDto examRoomRequestDto) throws EMException;

    boolean delete(Long id) throws EMException;

    ExamRoomResponseDto findById(Long id) throws EMException;

    Page<ExamRoomResponseDto> search(ExamRoomSearchDto dto) throws EMException;

    List<ExamRoomResponseDto> findAll();

    Boolean updateStatus(Long id, Integer status) throws EMException;
}
