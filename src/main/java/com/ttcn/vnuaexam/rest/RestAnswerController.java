package com.ttcn.vnuaexam.rest;

import com.ttcn.vnuaexam.dto.request.AnswerRequestDto;
import com.ttcn.vnuaexam.dto.request.ExamRequestDto;
import com.ttcn.vnuaexam.dto.request.QuestionRequestDto;
import com.ttcn.vnuaexam.dto.response.AnswerResponseDto;
import com.ttcn.vnuaexam.dto.response.QuestionResponseDto;
import com.ttcn.vnuaexam.dto.search.QuestionSearchDto;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.response.EMResponse;
import com.ttcn.vnuaexam.service.AnswerService;
import com.ttcn.vnuaexam.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
@RequiredArgsConstructor
public class RestAnswerController {
    private final AnswerService answerService;

    @GetMapping("/{id}")
    public EMResponse<List<AnswerResponseDto>> getByQuestionId(@PathVariable("id") Long id) {
        return new EMResponse<>(answerService.getByQuestionId(id));
    }

    @PutMapping("/{id}")
    public EMResponse<AnswerResponseDto> update (@PathVariable("id") Long id,
                                                 @RequestBody AnswerRequestDto answerRequestDto) throws EMException {
        return new EMResponse<>(answerService.update(id, answerRequestDto));
    }
}
