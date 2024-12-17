package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.constant.enums.ErrorCodeEnum;
import com.ttcn.vnuaexam.dto.response.FileDescriptionResponseDto;
import com.ttcn.vnuaexam.entity.FileDescription;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.repository.FileDescriptionRepository;
import com.ttcn.vnuaexam.service.FileDescriptionService;
import com.ttcn.vnuaexam.service.mapper.FileDescriptionMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileDescriptionServiceImpl implements FileDescriptionService {
    private final FileDescriptionRepository fileDescriptionRepository;
    private final FileDescriptionMapper fileDescriptionMapper;
    @Value("${upload.path}")
    private String uploadPath;
    private final String fileUpload = Paths.get("").toAbsolutePath().toString().concat("/EPT-Documents/");
    private static final String DOWNLOAD_FILE_CONTENT_TYPE = "application/octet-stream";


    @Override
    public FileDescriptionResponseDto upload(MultipartFile uploadFile) throws EMException {
        try {
            // Tạo tên tệp duy nhất
            String filename = generateUniqueFilename(uploadFile);

            // Đảm bảo thư mục tải lên tồn tại
            Path uploadDir = Paths.get(uploadPath);
            Files.createDirectories(uploadDir);

            // Lưu tệp vào đĩa
            Path targetLocation = uploadDir.resolve(filename);
            Files.copy(uploadFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            // Lưu metadata vào cơ sở dữ liệu
            FileDescription file = new FileDescription();
            file.setName(uploadFile.getOriginalFilename());
            file.setContentType(uploadFile.getContentType());
            file.setContentSize(uploadFile.getSize());
            file.setFilePath(targetLocation.toString());

            file = fileDescriptionRepository.save(file);

            return fileDescriptionMapper.entityToResponse(file);
        } catch (IOException e) {
            log.error("Tải tệp thất bại: {}", e.getMessage(), e);
            throw new EMException(ErrorCodeEnum.FILE_UPLOAD_FAILED);
        }
    }

    private String generateUniqueFilename(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename != null
                ? originalFilename.substring(originalFilename.lastIndexOf("."))
                : ".jpg";
        return UUID.randomUUID().toString() + extension;
    }

    public void viewFile(HttpServletResponse response, UUID id) throws IOException, EMException {
        try {
            FileDescription fileDescription = fileDescriptionRepository.findById(id).orElseThrow(() -> new EMException(ErrorCodeEnum.NOT_FOUND));
            File file = new File(fileUpload + fileDescription.getName());
            if (!file.exists()) {
                throw new EMException(ErrorCodeEnum.FILE_IS_ERROR);
            }
            response.setContentType(DOWNLOAD_FILE_CONTENT_TYPE);
            OutputStream outputStream = response.getOutputStream();
            FileInputStream in = new FileInputStream(file);
            IOUtils.copy(in, outputStream);
            outputStream.close();
            in.close();
        } catch (Exception e) {
            log.error("View file false with file id, ERROR: {} : {}", id, e.getMessage(), e);
        }
    }
}
