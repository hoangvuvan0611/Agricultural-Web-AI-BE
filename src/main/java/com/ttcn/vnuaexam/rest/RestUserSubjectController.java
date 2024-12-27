package com.ttcn.vnuaexam.rest;

import com.ttcn.vnuaexam.dto.request.UserSubjectRequestDto;
import com.ttcn.vnuaexam.dto.response.UserSubjectResponseDto;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.response.EMResponse;
import com.ttcn.vnuaexam.service.UserSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-subject")
@RequiredArgsConstructor
public class RestUserSubjectController {
    private final UserSubjectService userSubjectService;

    @PostMapping
    public EMResponse<UserSubjectResponseDto> create(@RequestBody UserSubjectRequestDto userSubjectRequestDto) throws EMException {
        return new EMResponse<>(userSubjectService.create(userSubjectRequestDto));
    }
}
