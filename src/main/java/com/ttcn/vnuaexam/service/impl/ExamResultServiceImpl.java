package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.dto.request.ExamResultRequestDto;
import com.ttcn.vnuaexam.dto.response.AnswerResponseDto;
import com.ttcn.vnuaexam.dto.response.ExamResultResponseDto;
import com.ttcn.vnuaexam.entity.ExamResult;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.repository.ExamResultRepository;
import com.ttcn.vnuaexam.service.ExamResultService;
import com.ttcn.vnuaexam.service.UserService;
import com.ttcn.vnuaexam.service.mapper.ExamResultMapper;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ExamResultServiceImpl implements ExamResultService {
    private final ExamResultRepository examResultRepository;
    private final ExamResultMapper examResultMapper;
    private final UserService userService;


    @Override
    @Transactional
    public ExamResultResponseDto submitExam(ExamResultRequestDto requestDto) throws EMException {
        var currentUser = userService.getCurrentUser();
        requestDto.setStudentId(currentUser.getId());

        var cachedAnswers = getStudentAnswers(requestDto.getExamId(), requestDto.getStudentId());
        cachedAnswers.addAll(requestDto.getAnswerList());
        saveProgress(requestDto);

        ExamResult examResult = new ExamResult();
        examResult.setExamId(requestDto.getExamId());
        examResult.setStudentId(requestDto.getStudentId());

        int correctCount = 0;
        var answerList = requestDto.getAnswerList();

        for (AnswerResponseDto answer : cachedAnswers) {
            if (answer == null) {
                continue;
            }

            if (answer.getIsCorrect()) {
                correctCount++;
            }
        }

        var totalScore = ((double) correctCount / answerList.size()) * 10.0;
        examResult.setTotalScore(totalScore);

        clearCache(requestDto.getExamId(), requestDto.getStudentId());
        return examResultMapper.entityToResponse(examResultRepository.save(examResult));
    }

    @CachePut(value = "student-answers", key = "'exam:' + #requestDto.examId + ':student:' + #requestDto.studentId")
    public List<AnswerResponseDto> saveProgress(ExamResultRequestDto requestDto) {
        return requestDto.getAnswerList();
    }

    @Cacheable(value = "student-answers", key = "'exam:' + #examId + ':student:' + #studentId")
    public List<AnswerResponseDto> getStudentAnswers(Long examId, Long studentId) {
        return new ArrayList<>();
    }

    @CacheEvict(value = "student-answers", key = "'exam:' + #examId + ':student:' + #studentId")
    public void clearCache(Long examId, Long studentId) {
    }
}
