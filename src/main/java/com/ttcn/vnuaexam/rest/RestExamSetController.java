package com.ttcn.vnuaexam.rest;

import com.ttcn.vnuaexam.dto.request.ExamSetRequestDto;
import com.ttcn.vnuaexam.dto.response.ExamSetResponseDto;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.response.EMResponse;
import com.ttcn.vnuaexam.service.ExamSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exam-sets")
@RequiredArgsConstructor
public class RestExamSetController {
    private final ExamSetService examSetService;

    @GetMapping("/{id}")
    public EMResponse<ExamSetResponseDto> getById(@PathVariable Long id) throws EMException {
        return new EMResponse<>(examSetService.getById(id));
    }

    @GetMapping("get-all")
    public EMResponse<List<ExamSetResponseDto>> getAll() {
        return new EMResponse<>(examSetService.getAll());
    }

    @PostMapping()
    public EMResponse<ExamSetResponseDto> create(@RequestBody ExamSetRequestDto requestDto) throws EMException {
        return new EMResponse<>(examSetService.create(requestDto));
    }

    @PutMapping("/{id}")
    public EMResponse<ExamSetResponseDto> update(@PathVariable Long id,
                                              @RequestBody ExamSetRequestDto requestDto) throws EMException {
        return new EMResponse<>(examSetService.update(id, requestDto));
    }
}
