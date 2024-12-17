package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.dto.request.SubjectRequestDto;
import com.ttcn.vnuaexam.dto.response.SubjectResponseDto;
import com.ttcn.vnuaexam.dto.search.SearchDto;
import com.ttcn.vnuaexam.entity.Subject;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.repository.ChapterRepository;
import com.ttcn.vnuaexam.repository.QuestionRepository;
import com.ttcn.vnuaexam.repository.SubjectRepository;
import com.ttcn.vnuaexam.service.QuestionService;
import com.ttcn.vnuaexam.service.SubjectService;
import com.ttcn.vnuaexam.service.mapper.SubjectMapper;
import com.ttcn.vnuaexam.utils.PageUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.ttcn.vnuaexam.constant.enums.ErrorCodeEnum.*;

@AllArgsConstructor
@Service
public class SubjectServiceImpl implements SubjectService {
    private final QuestionRepository questionRepository;
    private SubjectMapper subjectMapper;
    private SubjectRepository subjectRepository;
    private QuestionService questionService;
    private ChapterRepository chapterRepository;

    @Override
    public SubjectResponseDto getById(Long id) throws EMException {
        // Check id null, trống
        if (ObjectUtils.isEmpty(id)) {
            throw new EMException(SUBJECT_ID_IS_NOT_EXIST, e);
        }

        var subject = subjectRepository.findById(id).orElseThrow(() -> new EMException(NOT_FOUND, e));
        return subjectMapper.entityToResponse(subject);
    }

    @Override
    public SubjectResponseDto create(SubjectRequestDto requestDto) throws EMException {
        // validate
        validateSubject(requestDto, true);

        //tạo entity
        var subject = subjectMapper.requestToEntity(requestDto);
        subject = subjectRepository.save(subject);
        return subjectMapper.entityToResponse(subject);
    }

    @Override
    public SubjectResponseDto update(SubjectRequestDto requestDto, Long id) throws EMException {
        var subject = subjectRepository.findById(id).orElseThrow(() -> new EMException(NOT_FOUND_SUBJECT, e));

        requestDto.setId(id);
        validateSubject(requestDto, false);

        subjectMapper.setValue(requestDto, subject);
        subject = subjectRepository.save(subject);
        return subjectMapper.entityToResponse(subjectRepository.save(subject));
    }

    private void validateSubject(SubjectRequestDto requestDto, boolean isCreate) throws EMException {
        // Kiểm tra name trống
        if(!StringUtils.hasText(requestDto.getName())){
            throw new EMException(SUBJECT_NAME_IS_EMPTY, e);
        }

        // Kiểm tra name tồn tại chưa
        List<Subject> subjects;
        if (isCreate)
            subjects = subjectRepository.findByName(requestDto.getName());
        else
            subjects = subjectRepository.findByNameAndNotId(requestDto.getName(), requestDto.getId());

        if (!CollectionUtils.isEmpty(subjects))
            throw new EMException(SUBJECT_NAME_IS_EXIST, e);
    }

    // Xem lại
    @Override
    @Transactional(rollbackFor = {EMException.class})
    public Boolean deleteById(Long id) throws EMException {
        var subject = subjectRepository.findById(id).orElseThrow(() -> new EMException(NOT_FOUND_SUBJECT, e));
        var questions = questionRepository.findBySubjectId(subject.getId());
        List<Long> questionIds = new ArrayList<>();
        for (var question : questions) {
            var questionId = question.getId();
            questionIds.add(questionId);
        }

        questionService.deleteByIds(questionIds);
        chapterRepository.deleteBySubjectId(id);
        subjectRepository.delete(subject);
        return true;
    }

    @Override
    public Page<SubjectResponseDto> searchSubject(SearchDto dto) {
        Pageable pageRequest = PageUtils.getPageable(dto.getPageIndex(), dto.getPageSize());
        Page<Subject> resultEntity = subjectRepository.search(dto.getKeyword(), pageRequest);
        return resultEntity.map(subject -> subjectMapper.entityToResponse(subject));
    }
}
