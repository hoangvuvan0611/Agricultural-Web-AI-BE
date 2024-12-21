package com.ttcn.vnuaexam.rest;

import com.ttcn.vnuaexam.response.EMResponse;
import com.ttcn.vnuaexam.service.ExportService;
import com.ttcn.vnuaexam.service.ImportExcelService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/export")
@RequiredArgsConstructor
public class RestExportController {
    private final ExportService exportService;

    @PostMapping("/students/template")
    public void templateShippingStatus(HttpServletResponse response) throws IOException {
        ByteArrayResource excelFile = exportService.getTemplateImportStudent();
        InputStream ins = new ByteArrayInputStream(excelFile.getByteArray());
        IOUtils.copy(ins, response.getOutputStream());

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.addHeader("Content-Disposition", "attachment; filename=NHAP_SINH_VIEN.xlsx");
    }
}
