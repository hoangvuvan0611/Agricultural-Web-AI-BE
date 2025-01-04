package com.ttcn.vnuaexam.rest;

import com.ttcn.vnuaexam.dto.request.ExamRequestDto;
import com.ttcn.vnuaexam.dto.response.ExamResponseDto;
import com.ttcn.vnuaexam.dto.search.ExamSearchDto;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.response.EMResponse;
import com.ttcn.vnuaexam.service.ExamService;
import com.ttcn.vnuaexam.service.UserService;
import com.ttcn.vnuaexam.service.mapper.ExamMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exams")
@RequiredArgsConstructor
public class RestExamController {
    private final ExamService examService;
    private final UserService userService;

    @GetMapping("/{id}")
    public EMResponse<ExamResponseDto> getById(@PathVariable Long id) throws EMException {
        return new EMResponse<>(examService.getById(id));
    }

    @GetMapping("/exam-student/{examId}")
    public EMResponse<ExamResponseDto> getExamForStudent(@PathVariable Long examId) throws EMException {
        var studentId = userService.getCurrentUser().getId();
        return new EMResponse<>(examService.getExamForStudent(examId, studentId));
    }

    @PostMapping()
    public EMResponse<ExamResponseDto> create(@RequestBody ExamRequestDto requestDto) throws EMException {
        return new EMResponse<>(examService.create(requestDto));
    }

    @PutMapping("/{id}")
    public EMResponse<ExamResponseDto> update(@PathVariable Long id,
                                              @RequestBody ExamRequestDto requestDto) throws EMException {
        return new EMResponse<>(examService.update(requestDto, id));
    }

    @PostMapping("/add-question/{examId}")
    public EMResponse<String> saveQuestion(@PathVariable Long examId, @RequestBody ExamRequestDto requestDto) throws EMException {
        return new EMResponse<>(examService.saveQuestion(requestDto.getId(), requestDto.getQuestionIds()));
    }

    @GetMapping("/search")
    public EMResponse<Page<ExamResponseDto>> search(ExamSearchDto dto) {
        return new EMResponse<>(examService.search(dto));
    }

    @DeleteMapping("/{id}")
    public EMResponse<Boolean> delete(@PathVariable Long id) throws EMException {
        return new EMResponse<>(examService.deleteById(id));
    }
}
