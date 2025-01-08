package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.constant.Constant;
import com.ttcn.vnuaexam.dto.request.UserRequestDto;
import com.ttcn.vnuaexam.entity.Answer;
import com.ttcn.vnuaexam.entity.Question;
import com.ttcn.vnuaexam.repository.AnswerRepository;
import com.ttcn.vnuaexam.repository.ChapterRepository;
import com.ttcn.vnuaexam.repository.QuestionRepository;
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
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private static final Set<String> SUPPORTED_EXTENSIONS = Set.of(
            Constant.EXCEL_EXTENSION.XLSX,
            Constant.EXCEL_EXTENSION.XLS
    );
    private final ChapterRepository chapterRepository;

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

    /*
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

                List<UserRequestDto> requestDtoList = getDataFromFile(sheet);
                for (UserRequestDto userRequestDto : requestDtoList) {

                }


            }
        } catch (IOException e) {
            log.error("False to importShippingStatus : ERROR: {}", e.getMessage(), e);
        } finally {
            if (workbook != null)
                workbook.close();
        }
        return message.toString();
    }

     */

    @Override
    public String importQuestion(MultipartFile file, Long subjectId) throws IOException {
        List<Question> questions = new ArrayList<>();
        List<Answer> answers = new ArrayList<>();

        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                int cellIndex = 0;
                // Lưu câu hỏi
                Question question = new Question();
                String questionContent = ExcelUtils.getCellValue(row.getCell(cellIndex++));
                String chapterString = ExcelUtils.getCellValue(row.getCell(cellIndex++));
                var chapterList = chapterRepository.findByNameAndSubjectId(chapterString, subjectId);

                question.setContent(questionContent);
                if (!chapterList.isEmpty()) {
                    var chapterId = chapterList.getFirst().getId();
                    question.setChapterId(chapterId);
                }

                question.setSubjectId(subjectId);
                questions.add(question);

                List<Answer> questionAnswers = new ArrayList<>();
                boolean hasCorrectAnswer = false;

                // Đọc đáp án từ cột 1 đến 4
                for (int j = 2; j <= 5; j++) {
                    Cell answerCell = row.getCell(j);
                    if (answerCell == null || answerCell.getCellType() == CellType.BLANK) continue;

                    Answer answer = new Answer();
                    String answerContent = getCellValueAsString(answerCell);
                    answer.setContent(answerContent);
                    answer.setOrderNumber(j);
                    answer.setIsCorrect(j == 2); // Mặc định câu đầu tiên là đúng
                    questionAnswers.add(answer);

                    if (answer.getIsCorrect()) {
                        hasCorrectAnswer = true;
                    }
                }

                // Kiểm tra có ít nhất 1 đáp án đúng
                if (!questionAnswers.isEmpty() && hasCorrectAnswer) {
                    question = questionRepository.save(question);
                    System.out.println("Saved Question ID: " + question.getId());// Lưu câu hỏi trước
                    for (Answer ans : questionAnswers) {
                        ans.setQuestionId(question.getId()); // Gán ID câu hỏi
                    }
                    answerRepository.saveAll(questionAnswers);
                    System.out.println("Answers: " + answers);// Lưu danh sách đáp án
                    answers.addAll(questionAnswers);
                }
            }
        }

        return "Import completed. Questions: " + questions.size() + ", Answers: " + answers.size();
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue()); // Chuyển số thành chuỗi
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}
