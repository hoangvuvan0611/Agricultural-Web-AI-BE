package com.ttcn.vnuaexam.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImportExcelService {
    String importStudent(MultipartFile file) throws IOException;

    String addStudentToRoom(MultipartFile file, Long roomId) throws IOException;

    String importQuestion(MultipartFile file, Long subjectId) throws IOException;
}
