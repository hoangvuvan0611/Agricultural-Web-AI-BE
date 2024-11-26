package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.dto.request.AnswerRequestDto;
import com.ttcn.vnuaexam.dto.request.DepartmentRequestDto;
import com.ttcn.vnuaexam.dto.response.AnswerResponseDto;
import com.ttcn.vnuaexam.entity.Answer;
import com.ttcn.vnuaexam.entity.Department;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.repository.AnswerRepository;
import com.ttcn.vnuaexam.service.AnswerService;
import com.ttcn.vnuaexam.service.mapper.AnswerMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.ttcn.vnuaexam.constant.enums.ErrorCodeEnum.*;

@Service
@AllArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final AnswerMapper answerMapper;
    private final AnswerRepository answerRepository;

    @Override
    public AnswerResponseDto create(AnswerRequestDto requestDto) throws EMException {
        validateAnswer(requestDto, true);
        var answer = answerMapper.requestToEntity(requestDto);
        answerRepository.save(answer);
        return answerMapper.entityToResponse(answer);
    }

    private void validateAnswer(AnswerRequestDto requestDto, boolean isCreate) throws EMException {
        // Kiểm tra code, name trống
        if(!StringUtils.hasText(requestDto.getCode())){
            throw new EMException(CODE_IS_EMPTY);
        }

        // Kiểm tra code tồn tại chưa
        List<Answer> answers;
        if (isCreate)
            answers = answerRepository.findByCode(requestDto.getCode());
        else
            answers = answerRepository.findByCodeAndNotId(requestDto.getCode(), requestDto.getId());

        if (!CollectionUtils.isEmpty(answers))
            throw new EMException(DEPARTMENT_CODE_IS_EXIST);
    }


}
