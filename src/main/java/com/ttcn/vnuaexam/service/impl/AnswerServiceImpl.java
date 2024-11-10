package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.dto.request.AnswerRequestDto;
import com.ttcn.vnuaexam.dto.response.AnswerResponseDto;
import com.ttcn.vnuaexam.entity.Answer;
import com.ttcn.vnuaexam.repository.AnswerRepository;
import com.ttcn.vnuaexam.service.AnswerService;
import com.ttcn.vnuaexam.service.mapper.AnswerMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerMapper answerMapper;
    private final AnswerRepository answerRepository;

    @Override
    public AnswerResponseDto answerToResponse(Answer answer) {
        return answerMapper.entityToResponse(answer);
    }

    @Override
    public Answer requestToAnswer(AnswerRequestDto requestDto) {
        Answer answer = answerMapper.requestToEntity(requestDto);
        answerRepository.save(answer);
        return answer;
    }
}
