package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.dto.request.AnswerRequestDto;
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
import org.yaml.snakeyaml.emitter.EmitterException;

import java.util.ArrayList;
import java.util.HashSet;
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
        validateAnswer(requestDto.getAnswers());

        //Xử lý ảnh
        var question = questionMapper.requestToEntity(requestDto);
        question.setCountCorrect(countCorrect(requestDto.getAnswers()));
        questionRepository.save(question);

        // tạo response
        var response = questionMapper.entityToResponse(question);
        saveAnswer(response, requestDto.getAnswers());
        return response;
    }

    private void validateQuestion(QuestionRequestDto requestDto, boolean isCreate) throws EMException {
        // Kiểm tra code, name trống
        if(!StringUtils.hasText(requestDto.getContent())){
            throw new EMException(QUESTION_CONTENT_IS_EMPTY);
        }

        // Kiểm tra content tồn tại chưa
        List<Question> questions;
        if (isCreate)
            questions = questionRepository.findByContent(requestDto.getContent());
        else
            questions = questionRepository.findByContentAndNotId(requestDto.getContent(), requestDto.getId());

        if (!CollectionUtils.isEmpty(questions))
            throw new EMException(QUESTION_CODE_IS_EXIST);

        // Kiểm tra câu trả lời
    }

    private void validateAnswer(List<AnswerRequestDto> answers) throws EMException {
        List<String> answerContents = answers.stream().map(AnswerRequestDto::getContent).toList();

        // Phải có câu trả lời
        if (CollectionUtils.isEmpty(answerContents))
            throw new EMException(QUESTION_NO_ANSWER);

        // Câu trả lời không được trống
        if (answerContents.stream().anyMatch(String::isBlank))
            throw new EMException(ANSWER_BLANK);

        // Kiểm tra trùng lặp
        if (hasUniqueAnswers(answerContents))
            throw new EMException(DUPLICATE_ANSWER);

        // Kiểm tra đáp án đúng
        if (countCorrect(answers) < 1)
            throw new EMException(DO_NOT_HAVE_CORRECT_ANSWER);
    }

    private boolean hasUniqueAnswers(List<String> answers) {
        return new HashSet<>(answers).size() == answers.size();
    }

    private Integer countCorrect(List<AnswerRequestDto> answers) {
        if (!CollectionUtils.isEmpty(answers))
            return Math.toIntExact((answers.stream().filter(AnswerRequestDto::getIsCorrect).count()));
        return 1;
    }

    private void saveAnswer(QuestionResponseDto question ,List<AnswerRequestDto> answers) throws EMException {
        List<AnswerResponseDto> results = new ArrayList<>();
        for (AnswerRequestDto answer : answers) {
            answer.setQuestionId(question.getId());
            var answerResponse = answerService.create(answer);
            results.add(answerResponse);
        }
        question.setAnswers(results);
    }

    @Override
    @Transactional
    public QuestionResponseDto update(Long id, QuestionRequestDto requestDto) throws EMException {
        // Tìm question
        var question = questionRepository.findById(id)
                .orElseThrow(() -> new EMException(NOT_FOUND_QUESTION));

        // Validate question
        validateQuestion(requestDto, false);
        validateAnswer(requestDto.getAnswers());

        // Cập nhật question
        questionMapper.setValue(requestDto, question);
        question.setCountCorrect(countCorrect(requestDto.getAnswers()));
        questionRepository.save(question);
        var response = questionMapper.entityToResponse(question);

        // Xóa answers cũ
        answerService.deleteByQuestionId(question.getId());
        saveAnswer(response, requestDto.getAnswers());
        return response;
    }

    @Override
    public Boolean deleteById(Long id) throws EMException {
        var question = questionRepository.findById(id).orElseThrow(() -> new EMException(NOT_FOUND_QUESTION));
        answerService.deleteByQuestionId(id);
        questionRepository.delete(question);
        return true;
    }

//    @Override
//    public Page<QuestionResponseDto> search(QuestionSearchDto searchDto) {
//        return List.of();
//    }
}
