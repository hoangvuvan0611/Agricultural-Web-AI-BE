package com.ttcn.vnuaexam.service;

import com.ttcn.vnuaexam.dto.response.FileDescriptionResponseDto;
import com.ttcn.vnuaexam.exception.EMException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface FileDescriptionService {
    FileDescriptionResponseDto upload(MultipartFile uploadFile) throws EMException, IOException;

    void viewFile(HttpServletResponse response, UUID id) throws IOException, EMException;
}
