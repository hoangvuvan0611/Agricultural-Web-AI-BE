package com.ttcn.vnuaexam.rest;

import com.ttcn.vnuaexam.dto.request.AnswerRequestDto;
import com.ttcn.vnuaexam.dto.response.AnswerResponseDto;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.response.EMResponse;
import com.ttcn.vnuaexam.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
@RequiredArgsConstructor
public class RestAnswerController {
    private final AnswerService answerService;

    @GetMapping("by-question/{questionId}")
    public EMResponse<List<AnswerResponseDto>> getByQuestionId(@PathVariable("questionId") Long questionId) throws EMException {
        return new EMResponse<>(answerService.getByQuestionId(questionId));
    }

    @PutMapping("/{id}")
    public EMResponse<AnswerResponseDto> update (@PathVariable("id") Long id,
                                                 @RequestBody AnswerRequestDto answerRequestDto) throws EMException {
        return new EMResponse<>(answerService.update(id, answerRequestDto));
    }
}
