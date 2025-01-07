package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.config.UserSecurity;
import com.ttcn.vnuaexam.dto.request.ChapterRequestDto;
import com.ttcn.vnuaexam.dto.request.SubjectRequestDto;
import com.ttcn.vnuaexam.dto.response.ChapterResponseDto;
import com.ttcn.vnuaexam.dto.response.SubjectResponseDto;
import com.ttcn.vnuaexam.dto.search.SearchDto;
import com.ttcn.vnuaexam.dto.search.SubjectSearchDto;
import com.ttcn.vnuaexam.entity.Chapter;
import com.ttcn.vnuaexam.entity.Subject;
import com.ttcn.vnuaexam.entity.UserSubject;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.repository.ChapterRepository;
import com.ttcn.vnuaexam.repository.QuestionRepository;
import com.ttcn.vnuaexam.repository.SubjectRepository;
import com.ttcn.vnuaexam.repository.UserSubjectRepository;
import com.ttcn.vnuaexam.service.QuestionService;
import com.ttcn.vnuaexam.service.SubjectService;
import com.ttcn.vnuaexam.service.UserService;
import com.ttcn.vnuaexam.service.mapper.ChapterMapper;
import com.ttcn.vnuaexam.service.mapper.SubjectMapper;
import com.ttcn.vnuaexam.utils.PageUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.ttcn.vnuaexam.constant.enums.ErrorCodeEnum.*;
import static com.ttcn.vnuaexam.constant.enums.Role.ADMIN;
import static com.ttcn.vnuaexam.constant.enums.Role.TEACHER;

@AllArgsConstructor
@Service
public class SubjectServiceImpl implements SubjectService {
    private final QuestionRepository questionRepository;
    private final UserSubjectRepository userSubjectRepository;
    private final ChapterMapper chapterMapper;
    private SubjectMapper subjectMapper;
    private SubjectRepository subjectRepository;
    private QuestionService questionService;
    private ChapterRepository chapterRepository;
    private final UserService userService;

    @Override
    public SubjectResponseDto getById(Long id) throws EMException {
        // Check id null, trống
        if (ObjectUtils.isEmpty(id)) {
            throw new EMException(SUBJECT_ID_IS_NOT_EXIST);
        }

        var subject = subjectRepository.findById(id).orElseThrow(() -> new EMException(NOT_FOUND));
        var subjectResponse = subjectMapper.entityToResponse(subject);

        List<ChapterResponseDto> chapters = chapterRepository.findBySubjectId(subject.getId()).stream()
                .map(chapterMapper::entityToResponse).toList();
        subjectResponse.setChapters(chapters);
        return subjectResponse;
    }

    @Override
    @Transactional
    public SubjectResponseDto create(SubjectRequestDto requestDto) throws EMException {
        // validate
        validateSubject(requestDto, true);

        //tạo entity subject
        var subject = subjectMapper.requestToEntity(requestDto);
        subject = subjectRepository.save(subject);

        // Tạo quan hệ user subject
        var userId = userService.getCurrentUser().getId();
        userSubjectRepository.save(new UserSubject(userId, subject.getId()));

        // Luu chapter
        for (ChapterRequestDto request : requestDto.getChapters()) {
            request.setSubjectId(subject.getId());
            chapterRepository.save(chapterMapper.requestToEntity(request));
        }

        // trả ra response
        return subjectMapper.entityToResponse(subject);
    }

    @Override
    @Transactional
    public SubjectResponseDto update(SubjectRequestDto requestDto, Long id) throws EMException {
        var subject = subjectRepository.findById(id).orElseThrow(() -> new EMException(NOT_FOUND_SUBJECT));

        requestDto.setId(id);
        validateSubject(requestDto, false);

        // Update subject
        subjectMapper.setValue(requestDto, subject);
        subject = subjectRepository.save(subject);

        // Update chapter
        updateChapters(id, requestDto.getChapters());

        return subjectMapper.entityToResponse(subject);
    }

    private void validateSubject(SubjectRequestDto requestDto, boolean isCreate) throws EMException {
        // Kiểm tra name trống
        if(!StringUtils.hasText(requestDto.getName())){
            throw new EMException(SUBJECT_NAME_IS_EMPTY);
        }

        List<Subject> subjectCodes;
        if (isCreate)
            subjectCodes = subjectRepository.findByCode(requestDto.getCode());
        else
            subjectCodes = subjectRepository.findByCodeAndNotId(requestDto.getCode(), requestDto.getId());

        if (!CollectionUtils.isEmpty(subjectCodes))
            throw new EMException(SUBJECT_CODE_IS_EXIST);

        // Kiểm tra name tồn tại chưa
        List<Subject> subjects;
        if (isCreate)
            subjects = subjectRepository.findByName(requestDto.getName());
        else
            subjects = subjectRepository.findByNameAndNotId(requestDto.getName(), requestDto.getId());

        if (!CollectionUtils.isEmpty(subjects))
            throw new EMException(SUBJECT_NAME_IS_EXIST);
    }

    private void updateChapters(Long subjectId, List<ChapterRequestDto> requestDtos) throws EMException {
        for (ChapterRequestDto request : requestDtos) {
            request.setSubjectId(subjectId);
            if (request.getId() != null) {
                var existingChapter = chapterRepository.findById(request.getId()).orElseThrow(() -> new EMException(NOT_FOUND));
                chapterMapper.setValue(request, existingChapter);
                chapterRepository.save(existingChapter);
            } else {
                var newChapter = chapterMapper.requestToEntity(request);
                chapterRepository.save(newChapter);
            }
        }
    }

    // Xem lại
    @Override
    @Transactional
    public Boolean deleteById(Long id) throws EMException {
        var subject = subjectRepository.findById(id).orElseThrow(() -> new EMException(NOT_FOUND_SUBJECT));
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
    public Page<SubjectResponseDto> searchSubject(SubjectSearchDto dto) throws EMException {
        var userCurrent = userService.getCurrentUser();
        if (userCurrent != null && userCurrent.getRole() == TEACHER)
            dto.setUserId(userCurrent.getId());

        Pageable pageRequest = PageUtils.getPageable(dto.getPageIndex(), dto.getPageSize());
        return subjectRepository.search(dto, pageRequest);
    }
}
