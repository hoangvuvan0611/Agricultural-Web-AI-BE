package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.constant.enums.ErrorCodeEnum;
import com.ttcn.vnuaexam.dto.request.ExamRequestDto;
import com.ttcn.vnuaexam.dto.response.ExamResponseDto;
import com.ttcn.vnuaexam.entity.Exam;
import com.ttcn.vnuaexam.entity.ExamQuestion;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.repository.ExamQuestionRepository;
import com.ttcn.vnuaexam.repository.ExamRepository;
import com.ttcn.vnuaexam.repository.SubjectRepository;
import com.ttcn.vnuaexam.service.ExamService;
import com.ttcn.vnuaexam.service.QuestionService;
import com.ttcn.vnuaexam.service.mapper.ExamMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ExamServiceImpl implements ExamService {
    private final QuestionService questionService;
    private final ExamRepository examRepository;
    private final ExamMapper examMapper;
    private final ExamQuestionRepository examQuestionRepository;
    private final SubjectRepository subjectRepository;

    @Override
    public ExamResponseDto getById(Long id) throws EMException {
        // Lay de thi
        var exam = examRepository.findById(id).orElseThrow(() -> new EMException(ErrorCodeEnum.NOT_FOUND));

        //Lay danh sach id cau hoi
        var examQuestions = examQuestionRepository.findByExamId(exam.getId());
        List<Long> questionIds = examQuestions.stream().map(ExamQuestion::getQuestionId).toList();

        //Lay cau hoi va cau tra loi theo list id(service)
        var questionsResponses = questionService.getAllByIds(questionIds);

        // tra ra exam
        var result = examMapper.entityToResponse(exam);
        result.setQuestions(questionsResponses);
        return result;
    }

    @Override
    public ExamResponseDto create(ExamRequestDto examRequestDto) throws EMException {
        // validate request
        validateExam(examRequestDto);

        //Tạo mới Exam
        var newExam = examMapper.requestToEntity(examRequestDto);
        examRepository.save(newExam);
        return examMapper.entityToResponse(newExam);
    }

    @Override
    public ExamResponseDto update(ExamRequestDto examRequestDto, Long examId) throws EMException {
        //validate request

        // update exam
        Exam exam = examRepository.findById(examId).orElseThrow(() -> new EMException(ErrorCodeEnum.NOT_FOUND));
        examMapper.setValue(examRequestDto, exam);
        examRepository.save(exam);
        return examMapper.entityToResponse(exam);
    }

    private void validateExam(ExamRequestDto examRequestDto) throws EMException {
        if (!subjectRepository.existsById(examRequestDto.getSubjectId())) {
            throw new EMException(ErrorCodeEnum.SUBJECT_ID_IS_NOT_EXIST);
        }
    }

    @Override
    public ErrorCodeEnum saveQuestion(Long examId, List<Long> questionIds) throws EMException {
        var exam = examRepository.findById(examId).orElseThrow(() -> new EMException(ErrorCodeEnum.NOT_FOUND));
        int hadQuestion = exam.getHadQuestion();
        for (var questionId : questionIds) {
            if (isDuplicateQuestion(exam, questionId))
                continue;
            hadQuestion++;
            examQuestionRepository.save(new ExamQuestion(examId, questionId, hadQuestion));
        }
        exam.setHadQuestion(hadQuestion);
        examRepository.save(exam);
        return ErrorCodeEnum.SUCCESS;
    }

    private Boolean isDuplicateQuestion(Exam exam, Long questionId) {
        List<ExamQuestion> examQuestions = examQuestionRepository.findByExamId(exam.getId());
        return examQuestions.stream()
                .anyMatch(eq -> eq.getQuestionId().equals(questionId));
    }
}