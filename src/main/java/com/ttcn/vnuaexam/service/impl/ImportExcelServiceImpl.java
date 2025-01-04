package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.constant.Constant;
import com.ttcn.vnuaexam.dto.request.UserRequestDto;
import com.ttcn.vnuaexam.service.ImportExcelService;
import com.ttcn.vnuaexam.service.RoomStudentService;
import com.ttcn.vnuaexam.service.UserService;
import com.ttcn.vnuaexam.utils.ExcelUtils;
import com.ttcn.vnuaexam.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.ttcn.vnuaexam.constant.MessageCodes.EXCEL_EXTENSION_ERROR_MESSAGE;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImportExcelServiceImpl implements ImportExcelService {
    private final UserService userService;
    private final RoomStudentService roomStudentService;
    private static final Set<String> SUPPORTED_EXTENSIONS = Set.of(
            Constant.EXCEL_EXTENSION.XLSX,
            Constant.EXCEL_EXTENSION.XLS
    );

    @Override
    public String importStudent(MultipartFile file) throws IOException {
        validateFile(file);

        try (var inputStream = file.getInputStream();
             var workbook = createWorkbook(inputStream, getAndValidateExtension(file))) {

            Sheet sheet = workbook.getSheetAt(0);
            if (sheet == null) {
                throw new IllegalArgumentException("Excel file must contain at least one sheet");
            }

            List<UserRequestDto> students = extractStudentsFromSheet(sheet);
            return userService.importListStudent(students);

        } catch (IOException e) {
            log.error("Failed to process file: {}", file.getOriginalFilename(), e);
            throw new IOException("Failed to process Excel file", e);
        }
    }

    private void validateFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is required");
        }
    }

    private String getAndValidateExtension(MultipartFile file) {
        return FileUtils.getFileExtension(file.getOriginalFilename())
                .filter(ext -> SUPPORTED_EXTENSIONS.contains(ext.toLowerCase()))
                .orElseThrow(() -> new IllegalArgumentException(EXCEL_EXTENSION_ERROR_MESSAGE));
    }

    /**
     * Create appropriate workbook based on file extension
     */
    private Workbook createWorkbook(InputStream inputStream, String extension) throws IOException {
        return switch (extension.toLowerCase()) {
            case Constant.EXCEL_EXTENSION.XLSX -> new XSSFWorkbook(inputStream);
            case Constant.EXCEL_EXTENSION.XLS -> new HSSFWorkbook(inputStream);
            default -> throw new IllegalArgumentException(EXCEL_EXTENSION_ERROR_MESSAGE);
        };
    }

    /**
     * Extract student data from Excel sheet
     */
    private List<UserRequestDto> extractStudentsFromSheet(Sheet sheet) {
        return StreamSupport.stream(sheet.spliterator(), false)
                .skip(1) // Skip header row
                .filter(row -> !ExcelUtils.isRowEmpty(row))
                .map(this::mapRowToUserRequestDto)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Map Excel row to UserRequestDto
     */
    private UserRequestDto mapRowToUserRequestDto(Row row) {
        try {
            UserRequestDto dto = new UserRequestDto();
            int cellIndex = 0;

            dto.setCode(ExcelUtils.getCellValue(row.getCell(cellIndex++)));
            dto.setFullName(ExcelUtils.getCellValue(row.getCell(cellIndex++)));
            dto.setClassCode(ExcelUtils.getCellValue(row.getCell(cellIndex++)));
            dto.setDob(ExcelUtils.getCellValue(row.getCell(cellIndex++)));
            dto.setAddress(ExcelUtils.getCellValue(row.getCell(cellIndex)));

            // Validate required fields
            if (StringUtils.isAnyBlank(dto.getCode(), dto.getFullName(), dto.getClassCode())) {
                log.warn("Required fields missing in row {}", row.getRowNum());
                return null;
            }

            return dto;
        } catch (Exception e) {
            log.error("Error processing row {}", row.getRowNum(), e);
            return null;
        }
    }

    @Override
    public String addStudentToRoom(MultipartFile file, Long roomId) throws IOException {
        validateFile(file);

        try (var inputStream = file.getInputStream();
             var workbook = createWorkbook(inputStream, getAndValidateExtension(file))) {
            var studentCodes = extractStudentCodeFromFile(workbook.getSheetAt(0));
            return roomStudentService.addStudentToRoom(studentCodes, roomId);
        }
    }

    private List<String> extractStudentCodeFromFile(Sheet sheet) {
        return StreamSupport.stream(sheet.spliterator(), false)
                .skip(1)
                .filter(row -> !ExcelUtils.isRowEmpty(row))
                .map(row -> ExcelUtils.getCellValue(row.getCell(0)))
                .toList();
    }

    @Override
    public String importQuestion(MultipartFile file) throws IOException {
        // tạo message
        StringBuilder message = new StringBuilder();
        Workbook workbook = null; // <-Interface, accepts both HSSF and XSSF.
        Sheet sheet;

        // Duyệt file
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(file.getBytes())) {
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            switch (Objects.requireNonNull(extension)) {
                case Constant.EXCEL_EXTENSION.XLSX:
                    workbook = new XSSFWorkbook(byteArrayInputStream);
                    sheet = workbook.getSheetAt(0);
                    break;
                case Constant.EXCEL_EXTENSION.XLS:
                    workbook = new HSSFWorkbook(byteArrayInputStream);
                    sheet = workbook.getSheetAt(0);
                    break;
                default:
                    return EXCEL_EXTENSION_ERROR_MESSAGE;
            }
            if (sheet != null) {
                /*
                List<UserRequestDto> requestDtoList = getDataFromFile(sheet);
                for (UserRequestDto userRequestDto : requestDtoList) {

                }

                 */
            }
        } catch (IOException e) {
            log.error("False to importShippingStatus : ERROR: {}", e.getMessage(), e);
        } finally {
            if (workbook != null)
                workbook.close();
        }
        return message.toString();
    }
}
