package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.dto.request.QuestionRequestDto;
import com.ttcn.vnuaexam.dto.response.QuestionResponseDto;
import com.ttcn.vnuaexam.entity.Question;
import com.ttcn.vnuaexam.repository.QuestionRepository;
import com.ttcn.vnuaexam.service.QuestionService;
import com.ttcn.vnuaexam.service.mapper.QuestionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    @Override
    public QuestionResponseDto questionToResponse(Question question) {
        return questionMapper.entityToDto(question);
    }

    @Override
    public Question RequestToQuestion(QuestionRequestDto questionRequestDto) {

        Question question = questionMapper.requestDtoToEntity(questionRequestDto);
        questionRepository.save(question);

        return question;
    }
}
