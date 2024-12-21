package com.ttcn.vnuaexam.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExportService {

    public ByteArrayResource getTemplateImportStudent() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            String template = "NHAP_SINH_VIEN.xlsx";
            ClassPathResource classPathResource = new ClassPathResource("excel/" + template);
            InputStream inputStream = classPathResource.getInputStream();
            Workbook workbook = new XSSFWorkbook(inputStream);
            workbook.write(outputStream);
            workbook.close();
            inputStream.close();
        } catch (Exception e) {
            log.error("Không thể tải xuống file mẫu nhập sinh viên : ERROR: {}",e.getMessage(),e);
        }
        return new ByteArrayResource(outputStream.toByteArray());
    }
}
