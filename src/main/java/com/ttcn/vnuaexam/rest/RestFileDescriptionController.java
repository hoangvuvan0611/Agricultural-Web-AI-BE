package com.ttcn.vnuaexam.rest;

import com.ttcn.vnuaexam.dto.request.ExamRequestDto;
import com.ttcn.vnuaexam.dto.response.ExamResponseDto;
import com.ttcn.vnuaexam.dto.response.FileDescriptionResponseDto;
import com.ttcn.vnuaexam.entity.FileDescription;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.response.EMResponse;
import com.ttcn.vnuaexam.service.ExamService;
import com.ttcn.vnuaexam.service.FileDescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class RestFileDescriptionController {
    private final FileDescriptionService fileDescriptionService;

    /*
    @GetMapping("/{id}")
    public EMResponse<ExamResponseDto> getById(@PathVariable Long id) throws EMException {
        return new EMResponse<>(fileDescriptionService.getById(id));
    }

     */

    @PostMapping()
    public EMResponse<FileDescriptionResponseDto> upload(@RequestParam("upload-file") MultipartFile uploadFile) throws EMException, IOException {
        return new EMResponse<>(fileDescriptionService.upload(uploadFile));
    }

    /*
    @PutMapping("/{id}")
    public EMResponse<ExamResponseDto> update(@PathVariable Long id,
                                              @RequestBody ExamRequestDto requestDto) throws EMException {
        return new EMResponse<>(examService.update(requestDto, id));
    }

    @PostMapping("/question")
    public EMResponse<String> saveQuestion(@RequestBody ExamRequestDto requestDto) throws EMException {
        return new EMResponse<>(examService.saveQuestion(requestDto.getId(), requestDto.getQuestionIds()));
    }

     */
}
