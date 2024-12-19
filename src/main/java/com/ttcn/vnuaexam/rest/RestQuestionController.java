package com.ttcn.vnuaexam.rest;

import com.ttcn.vnuaexam.dto.request.ExamRequestDto;
import com.ttcn.vnuaexam.dto.request.QuestionRequestDto;
import com.ttcn.vnuaexam.dto.response.QuestionResponseDto;
import com.ttcn.vnuaexam.dto.response.SubjectResponseDto;
import com.ttcn.vnuaexam.dto.search.QuestionSearchDto;
import com.ttcn.vnuaexam.dto.search.SearchDto;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.response.EMResponse;
import com.ttcn.vnuaexam.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class RestQuestionController {
    private final QuestionService questionService;

    @GetMapping("/{id}")
    public EMResponse<QuestionResponseDto> getById(@PathVariable("id") Long id) throws EMException {
        return new EMResponse<>(questionService.getById(id));
    }

    @PostMapping()
    public EMResponse<QuestionResponseDto> create(@RequestBody QuestionRequestDto requestDto) throws EMException {
        return new EMResponse<>(questionService.create(requestDto));
    }

    @PutMapping("/{id}")
    public EMResponse<QuestionResponseDto> update(@RequestBody QuestionRequestDto requestDto, @PathVariable("id") Long id) throws EMException {
        return new EMResponse<>(questionService.update(id, requestDto));
    }

    @DeleteMapping("/list-question")
    public EMResponse<String> delete(@RequestBody ExamRequestDto dto) throws EMException {
        return new EMResponse<>(questionService.deleteByIds(dto.getQuestionIds()));
    }

    @GetMapping("/search")
    public EMResponse<Page<QuestionResponseDto>> search(QuestionSearchDto dto) {
        return new EMResponse<>(questionService.search(dto));
    }
}
