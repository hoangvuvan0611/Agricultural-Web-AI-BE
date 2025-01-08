package com.ttcn.vnuaexam.rest;

import com.ttcn.vnuaexam.dto.request.ExamResultRequestDto;
import com.ttcn.vnuaexam.dto.response.ExamResultResponseDto;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.response.EMResponse;
import com.ttcn.vnuaexam.service.ExamResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/submit")
@RequiredArgsConstructor
public class RestExamResultController {
    private final ExamResultService examResultService;

    // Nộp bài
    @PostMapping()
    public EMResponse<ExamResultResponseDto> submit(@RequestBody ExamResultRequestDto requestDto) throws EMException {
        return new EMResponse<>(examResultService.submitExam(requestDto));
    }
}
