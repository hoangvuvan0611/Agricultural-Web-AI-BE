package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.dto.request.QuestionRequestDto;
import com.ttcn.vnuaexam.dto.response.AnswerResponseDto;
import com.ttcn.vnuaexam.dto.response.QuestionResponseDto;
import com.ttcn.vnuaexam.entity.Answer;
import com.ttcn.vnuaexam.entity.Question;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.repository.AnswerRepository;
import com.ttcn.vnuaexam.repository.QuestionRepository;
import com.ttcn.vnuaexam.service.AnswerService;
import com.ttcn.vnuaexam.service.QuestionService;
import com.ttcn.vnuaexam.service.mapper.AnswerMapper;
import com.ttcn.vnuaexam.service.mapper.QuestionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.ttcn.vnuaexam.constant.enums.ErrorCodeEnum.*;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final AnswerMapper answerMapper;
    private final AnswerRepository answerRepository;
    private final AnswerService answerService;

    @Override
    public QuestionResponseDto getById(Long id) throws EMException {
        if (ObjectUtils.isEmpty(id)) {
            throw new EMException(QUESTION_ID_IS_NOT_EXIST);
        }

        var question = questionRepository.findById(id).orElseThrow(() -> new EMException(NOT_FOUND_QUESTION));
        List<Answer> answers = answerRepository.findByQuestionId(question.getId());

        List<AnswerResponseDto> answerResponses = answers.stream()
                .filter(Objects::nonNull)
                .map(answerMapper::entityToResponse)
                .collect(Collectors.toList());

        var result = questionMapper.entityToResponse(question);
        result.setAnswers(answerResponses);
        return result;
    }

    @Override
    @Transactional
    public QuestionResponseDto create(QuestionRequestDto requestDto) throws EMException {
        validateQuestion(requestDto, true);

        //Lưu question
        var question = questionMapper.requestToEntity(requestDto);
        questionRepository.save(question);

        //Map answer sử dụng stream
        List<AnswerResponseDto> answers = List.of();
        if (requestDto.getAnswers() != null) {
            answers = requestDto.getAnswers().stream()
                    .map(requestAnswer -> {
                        requestAnswer.setQuestionId(question.getId());
                        try {
                            return answerService.create(requestAnswer);
                        } catch (EMException e) {
                            throw new RuntimeException(e);
                        }
                    }).collect(Collectors.toList());
        }

        // tạo response
        var response = questionMapper.entityToResponse(question);
        if (!CollectionUtils.isEmpty(answers)) {
            response.setAnswers(answers);
        }
        return response;
    }

    private void validateQuestion(QuestionRequestDto requestDto, boolean isCreate) throws EMException {
        // Kiểm tra code, name trống
        if(!StringUtils.hasText(requestDto.getCode())){
            throw new EMException(CODE_IS_EMPTY);
        }

        // Kiểm tra code tồn tại chưa
        List<Question> questions;
        if (isCreate)
            questions = questionRepository.findByCode(requestDto.getCode());
        else
            questions = questionRepository.findByCodeAndNotId(requestDto.getCode(), requestDto.getId());

        if (!CollectionUtils.isEmpty(questions))
            throw new EMException(QUESTION_CODE_IS_EXIST);
    }

    @Override
    public QuestionResponseDto update(Long id, QuestionRequestDto requestDto) throws EMException {
        var question = questionRepository.findById(id).orElseThrow(() -> new EMException(NOT_FOUND_QUESTION));

        validateQuestion(requestDto, false);

        questionMapper.setValue(requestDto, question);

        //update câu trả lời

        questionRepository.save(question);
        return questionMapper.entityToResponse(question);
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

//    @Override
//    public Page<QuestionResponseDto> search(QuestionSearchDto searchDto) {
//        return List.of();
//    }
}
