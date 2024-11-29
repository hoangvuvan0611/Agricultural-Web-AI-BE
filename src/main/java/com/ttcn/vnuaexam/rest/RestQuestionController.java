package com.ttcn.vnuaexam.rest;

import com.ttcn.vnuaexam.dto.request.QuestionRequestDto;
import com.ttcn.vnuaexam.dto.response.QuestionResponseDto;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.response.EMResponse;
import com.ttcn.vnuaexam.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{id}")
    public EMResponse<Boolean> delete(@PathVariable("id") Long id) throws EMException {
        return new EMResponse<>(questionService.deleteById(id));
    }

//    @GetMapping("/search")
//    public EMResponse<Page<QuestionResponseDto>> search(QuestionSearchDto searchDto){
//        return new EMResponse<>(questionService.search(searchDto));
//    }
}
