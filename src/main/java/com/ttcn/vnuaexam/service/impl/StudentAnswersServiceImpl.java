package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.dto.request.StudentAnswersRequestDto;
import com.ttcn.vnuaexam.dto.response.StudentAnswersResponseDto;
import com.ttcn.vnuaexam.entity.StudentAnswers;
import com.ttcn.vnuaexam.repository.StudentAnswersRepository;
import com.ttcn.vnuaexam.service.StudentAnswersService;
import com.ttcn.vnuaexam.service.mapper.StudentAnswersMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentAnswersServiceImpl implements StudentAnswersService {

    private StudentAnswersMapper studentAnswersMapper;
    private StudentAnswersRepository studentAnswersRepository;

}
