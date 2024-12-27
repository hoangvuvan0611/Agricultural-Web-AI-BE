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
@RequestMapping("/exam-rooms")
@RequiredArgsConstructor
public class RestExamRoomController {
    private final ExamRoomService examRoomService;

    @GetMapping("/")
    public EMResponse<List<ExamRoomResponseDto>> getExamSession() {
        return new EMResponse<>(examRoomService.findAll());
    }

    @PostMapping("/add")
    public EMResponse<ExamRoomResponseDto> addExamSession(@RequestBody ExamRoomRequestDto requestDto) {
        return new EMResponse<>(examRoomService.add(requestDto));
    }

    @PutMapping("/{id}")
    public EMResponse<ExamRoomResponseDto> updateExamSession(@PathVariable Long id, @RequestBody ExamRoomRequestDto requestDto) throws EMException {
        return new EMResponse<>(examRoomService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public EMResponse<Boolean> deleteExamSession(@PathVariable Long id) throws EMException {
        return new EMResponse<>(examRoomService.delete(id));
    }

    @GetMapping("/{id}")
    public EMResponse<ExamRoomResponseDto> getExamSession(@PathVariable Long id) throws EMException {
        return new EMResponse<>(examRoomService.findById(id));
    }
}
