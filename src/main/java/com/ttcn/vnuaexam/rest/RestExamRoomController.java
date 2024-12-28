package com.ttcn.vnuaexam.rest;

import com.ttcn.vnuaexam.dto.request.ExamRoomRequestDto;
import com.ttcn.vnuaexam.dto.response.ExamRoomResponseDto;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.response.EMResponse;
import com.ttcn.vnuaexam.service.ExamRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exam-rooms")
@RequiredArgsConstructor
public class RestExamRoomController {
    private final ExamRoomService examRoomService;

    @GetMapping()
    public EMResponse<List<ExamRoomResponseDto>> getAll() {
        return new EMResponse<>(examRoomService.findAll());
    }

    @PostMapping("/add")
    public EMResponse<ExamRoomResponseDto> create(@RequestBody ExamRoomRequestDto requestDto) throws EMException {
        return new EMResponse<>(examRoomService.add(requestDto));
    }

    @PutMapping("/{id}")
    public EMResponse<ExamRoomResponseDto> update(@PathVariable Long id, @RequestBody ExamRoomRequestDto requestDto) throws EMException {
        return new EMResponse<>(examRoomService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public EMResponse<Boolean> delete(@PathVariable Long id) throws EMException {
        return new EMResponse<>(examRoomService.delete(id));
    }

    @GetMapping("/{id}")
    public EMResponse<ExamRoomResponseDto> getById(@PathVariable Long id) throws EMException {
        return new EMResponse<>(examRoomService.findById(id));
    }
}
