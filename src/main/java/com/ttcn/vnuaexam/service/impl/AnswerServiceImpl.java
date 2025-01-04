package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.constant.enums.ErrorCodeEnum;
import com.ttcn.vnuaexam.dto.request.AnswerRequestDto;
import com.ttcn.vnuaexam.dto.response.AnswerResponseDto;
import com.ttcn.vnuaexam.entity.Answer;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.repository.AnswerRepository;
import com.ttcn.vnuaexam.repository.QuestionRepository;
import com.ttcn.vnuaexam.service.AnswerService;
import com.ttcn.vnuaexam.service.mapper.AnswerMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final AnswerMapper answerMapper;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Override
    public AnswerResponseDto create(AnswerRequestDto requestDto) throws EMException {
        var answer = answerMapper.requestToEntity(requestDto);
        answerRepository.save(answer);
        return answerMapper.entityToResponse(answer);
    }

    @Override
    public Boolean deleteById(Long id) throws EMException {
        if (!answerRepository.existsById(id)) {
            throw new EMException(ErrorCodeEnum.NOT_FOUND);
        }
        answerRepository.deleteById(id);
        return true;
    }

    @Override
    public List<AnswerResponseDto> getByQuestionId(Long questionId) throws EMException {
        if (!questionRepository.existsById(questionId))
            throw new EMException(ErrorCodeEnum.NOT_FOUND);
        List<AnswerResponseDto> answersResponse;
        List<Answer> answers = answerRepository.findByQuestionId(questionId);
        answersResponse = answers.stream().map(answerMapper::entityToResponse).toList();
        return answersResponse;
    }

    @Override
    public AnswerResponseDto update(Long id, AnswerRequestDto requestDto) throws EMException {
        var answer = answerRepository.findById(id).orElseThrow(() -> new EMException(ErrorCodeEnum.NOT_FOUND));
        answerMapper.setValue(requestDto, answer);
        return answerMapper.entityToResponse(answer);
    }
}
