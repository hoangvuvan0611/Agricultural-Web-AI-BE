package com.ttcn.vnuaexam.rest;

import com.ttcn.vnuaexam.dto.response.FileDescriptionResponseDto;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.response.EMResponse;
import com.ttcn.vnuaexam.service.FileDescriptionService;
import com.ttcn.vnuaexam.service.ImportExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/import-excel")
@RequiredArgsConstructor
public class RestImportExcelController {
    private final ImportExcelService importExcelService;

    @PostMapping("/students")
    public EMResponse<String> importStudent(@RequestParam("file") MultipartFile file) throws IOException {
        return new EMResponse<>(importExcelService.importStudent(file));
    }
}
