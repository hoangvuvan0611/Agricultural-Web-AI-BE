package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.constant.enums.ErrorCodeEnum;
import com.ttcn.vnuaexam.constant.enums.Role;
import com.ttcn.vnuaexam.constant.enums.StatusExamRoomEnum;
import com.ttcn.vnuaexam.corollary.ExamResultSetResponse;
import com.ttcn.vnuaexam.dto.request.ExamRoomRequestDto;
import com.ttcn.vnuaexam.dto.response.ExamResponseDto;
import com.ttcn.vnuaexam.dto.response.ExamRoomResponseDto;
import com.ttcn.vnuaexam.dto.search.ExamRoomSearchDto;
import com.ttcn.vnuaexam.dto.search.SearchDto;
import com.ttcn.vnuaexam.entity.ExamRoom;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.repository.ExamRepository;
import com.ttcn.vnuaexam.repository.ExamRoomRepository;
import com.ttcn.vnuaexam.service.ExamRoomService;
import com.ttcn.vnuaexam.service.UserService;
import com.ttcn.vnuaexam.service.mapper.ExamRoomMapper;
import com.ttcn.vnuaexam.utils.PageUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ExamRoomServiceImpl implements ExamRoomService {
    private final UserService userService;
    private final ExamRoomMapper examRoomMapper;
    private final ExamRoomRepository examRoomRepository;
    private final ExamRepository examRepository;

    @Override
    public ExamRoomResponseDto add(ExamRoomRequestDto requestDto) throws EMException {
        var currentUser = userService.getCurrentUser();
        requestDto.setTeacherId(currentUser.getId());
        var examSession = examRoomMapper.requestDtoToEntity(requestDto);
        examSession.setStatus(StatusExamRoomEnum.COMING_SON.getCode());
        examRoomRepository.save(examSession);
        return examRoomMapper.entityToResponseDto(examSession);
    }

    @Override
    public ExamRoomResponseDto update(Long id, ExamRoomRequestDto ExamRoomRequestDto) throws EMException {
        var ExamSession = examRoomRepository.findById(id)
                .orElseThrow(() -> new EMException(ErrorCodeEnum.NOT_FOUND_EXAMSESSION));
        examRoomMapper.setValue(ExamRoomRequestDto, ExamSession);
        examRoomRepository.save(ExamSession);
        return examRoomMapper.entityToResponseDto(ExamSession);
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
        return examRoomMapper.entityToResponseDto(examSession);
    }

    @Override
    public Page<ExamRoomResponseDto> search(ExamRoomSearchDto dto) throws EMException {
        var currentUser = userService.getCurrentUser();
        if (currentUser != null && currentUser.getRole().equals(Role.TEACHER))
            dto.setUserId(currentUser.getId());

        Pageable pageRequest = PageUtils.getPageable(dto.getPageIndex(), dto.getPageSize());
        return examRoomRepository.search(dto, pageRequest);
    }

    @Override
    public List<ExamRoomResponseDto> findAll() {
        List<ExamRoom> examSessions = examRoomRepository.findAll();
        return examSessions.stream()
                .map(examRoomMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean updateStatus(Long id, Integer status) throws EMException {
        var examRoom = examRoomRepository.findById(id).orElseThrow(() -> new EMException(ErrorCodeEnum.NOT_FOUND));
        examRoom.setStatus(status);
        examRoomRepository.save(examRoom);
        return true;
    }
}
