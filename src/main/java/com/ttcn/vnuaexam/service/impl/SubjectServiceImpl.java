package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.corollary.SubjectResultSetResponse;
import com.ttcn.vnuaexam.dto.request.SubjectRequestDto;
import com.ttcn.vnuaexam.dto.response.SubjectResponseDto;
import com.ttcn.vnuaexam.dto.search.SubjectSearchDto;
import com.ttcn.vnuaexam.entity.Subject;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.repository.DepartmentRepository;
import com.ttcn.vnuaexam.repository.SubjectRepository;
import com.ttcn.vnuaexam.service.SubjectService;
import com.ttcn.vnuaexam.service.mapper.SubjectMapper;
import com.ttcn.vnuaexam.utils.PageUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.ttcn.vnuaexam.constant.enums.ErrorCodeEnum.*;

@AllArgsConstructor
@Service
public class SubjectServiceImpl implements SubjectService {
    private final DepartmentRepository departmentRepository;
    private SubjectMapper subjectMapper;
    private SubjectRepository subjectRepository;

    @Override
    public SubjectResponseDto getById(Long id) throws EMException {
        // Check id null, trống
        if (ObjectUtils.isEmpty(id)) {
            throw new EMException(SUBJECT_ID_IS_NOT_EXIST);
        }

        var subject = subjectRepository.findById(id).orElseThrow(() -> new EMException(NOT_FOUND));
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

    private void validateSubject(SubjectRequestDto requestDto, boolean isCreate) throws EMException {
        // Kiểm tra code, name trống
        if(!StringUtils.hasText(requestDto.getCode()) || !StringUtils.hasText(requestDto.getName())){
            throw new EMException(SUBJECT_NAME_OR_CODE_IS_EMPTY);
        }

        // Kiểm tra code tồn tại chưa
        List<Subject> subjects;
        if (isCreate)
            subjects = subjectRepository.findByCode(requestDto.getCode());
        else
            subjects = subjectRepository.findByCodeAndNotId(requestDto.getCode(), requestDto.getId());

        if (!CollectionUtils.isEmpty(subjects))
            throw new EMException(SUBJECT_CODE_IS_EXIST);
    }

    @Override
    public SubjectResponseDto update(SubjectRequestDto requestDto, Long id) throws EMException {
        var subject = subjectRepository.findById(id).orElseThrow(() -> new EMException(NOT_FOUND_SUBJECT));

        validateSubject(requestDto, false);

        subjectMapper.setValue(requestDto, subject);
        subject = subjectRepository.save(subject);
        return subjectMapper.entityToResponse(subjectRepository.save(subject));
    }

    @Override
    public Boolean deleteById(Long id) throws EMException {
        var subject = subjectRepository.findById(id).orElseThrow(() -> new EMException(NOT_FOUND_SUBJECT));
        subjectRepository.delete(subject);
        return true;
    }

    @Override
    public Page<SubjectResponseDto> searchSubject(SubjectSearchDto searchDto) {
        Pageable pageRequest = PageUtils.getPageable(searchDto.getPageIndex(), searchDto.getPageSize());
        Page<SubjectResultSetResponse> pageResults = subjectRepository.search(searchDto, pageRequest);
        return pageResults.map(SubjectResponseDto::new);
    }
}
