package com.ttcn.vnuaexam.service;

import com.ttcn.vnuaexam.dto.request.ExamResultRequestDto;
import com.ttcn.vnuaexam.dto.response.ExamResultResponseDto;
import com.ttcn.vnuaexam.entity.ExamResult;
import com.ttcn.vnuaexam.exception.EMException;

public interface ExamResultService {
    ExamResultResponseDto submitExam(ExamResultRequestDto requestDto) throws EMException;
}
