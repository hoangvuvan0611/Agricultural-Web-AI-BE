package com.ttcn.vnuaexam.rest;

import com.ttcn.vnuaexam.dto.request.ExamSessionRequestDto;
import com.ttcn.vnuaexam.dto.response.ExamSessionResponseDto;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.response.EMResponse;
import com.ttcn.vnuaexam.service.ExamSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam-session")
@RequiredArgsConstructor
public class RestExamSessionController {
    private final ExamSessionService examSessionService;

    @GetMapping("/")
    public EMResponse<List<ExamSessionResponseDto>> getExamSession() {
        return new EMResponse<>(examSessionService.findAll());
    }

    @PostMapping("/add")
    public EMResponse<ExamSessionResponseDto> addExamSession(@RequestBody ExamSessionRequestDto requestDto) {
        return new EMResponse<>(examSessionService.add(requestDto));
    }

    @PutMapping("/{id}")
    public EMResponse<ExamSessionResponseDto> updateExamSession(@PathVariable Long id, @RequestBody ExamSessionRequestDto requestDto) throws EMException {
        return new EMResponse<>(examSessionService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public EMResponse<Boolean> deleteExamSession(@PathVariable Long id) throws EMException {
        return new EMResponse<>(examSessionService.delete(id));
    }

    @GetMapping("/{id}")
    public EMResponse<ExamSessionResponseDto> getExamSession(@PathVariable Long id) throws EMException {
        return new EMResponse<>(examSessionService.findById(id));
    }
}
