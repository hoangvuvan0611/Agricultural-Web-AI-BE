package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.constant.enums.ErrorCodeEnum;
import com.ttcn.vnuaexam.dto.request.AnswerRequestDto;
import com.ttcn.vnuaexam.dto.response.AnswerResponseDto;
import com.ttcn.vnuaexam.exception.EMException;
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
    public AnswerResponseDto create(AnswerRequestDto requestDto) throws EMException {
        var answer = answerMapper.requestToEntity(requestDto);
        answerRepository.save(answer);
        return answerMapper.entityToResponse(answer);
    }

    @Override
    public void deleteByQuestionId(Long id) throws EMException {
        if (!answerRepository.existsById(id)) {
            throw new EMException(ErrorCodeEnum.NOT_FOUND);
        }
        answerRepository.deleteById(id);
    }

}
