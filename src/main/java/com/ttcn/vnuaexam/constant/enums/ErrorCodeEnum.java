package com.ttcn.vnuaexam.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {
    SUCCESS(200, "Success"),
    BAD_REQUEST(400, "Bad Request"),
    NOT_FOUND(404, "Not Found"),
    CHAPTER_NAME_IS_EMPTY(400_001, "Chapter name is empty"),
    CHAPTER_NAME_IS_EXIST(400_002, "Chapter name is exist in subject"),
    CHAPTER_ID_IS_NOT_EXIST(400_003, "Chapter id is not exist"),
    SUBJECT_ID_IS_NOT_EXIST(400_004, "Subject id is not exist"),
    SUBJECT_NAME_IS_EMPTY(400_005, "Subject name is empty"),
    SUBJECT_NAME_IS_EXIST(400_006, "Subject name is exist"),
    SUBJECT_CODE_IS_EXIST(400_0006, "Subject code is exist"),
    NOT_FOUND_CHAPTER(400_007, "Not Found chapter"),
    NOT_FOUND_SUBJECT(400_008, "Not Found Subject"),
    QUESTION_ID_IS_NOT_EXIST(400_009, "Question id is not exist"),
    NOT_FOUND_QUESTION(400_0010, "Not Found Question"),
    QUESTION_CODE_IS_EXIST(400_0011, "Question code is exist"),
    QUESTION_CONTENT_IS_EMPTY(400_0012, "Question content is empty"),
    ANSWER_CONTENT_IS_EMPTY(400_0012, "Answer content is empty"),
    ANSWER_CODE_IS_EXIST(400_0014, "Answer code is exist"),
    CANNOT_CREATE_ANSWER(400_0015, "Can't create answer"),
    USER_CODE_IS_NOT_EXIST(400_0016, "User code is not exist"),
    USER_CODE_ALREADY_EXISTS(400_0017, "User code already exists"),
    NOT_FOUND_USER(400_0018, "Not Found User"),
    NOT_FOUND_USERNAME(400_0018, "Not Found User Name"),
    USER_NAME_NOT_EMPTY(400_0019, "User name is empty"),
    USER_NAME_ALREADY_EXISTS(400_0017, "User name already exists"),
    UNAUTHENTICATED(400_0018, "Unauthenticated"),
    QUESTION_NO_ANSWER(400_0019, "Question doesn't have answer"),
    ANSWER_BLANK(400_0020, "Answer is blank"),
    DUPLICATE_ANSWER(400_0021, "Duplicate answer"),
    DO_NOT_HAVE_CORRECT_ANSWER(400_0022, "Do not have correct answer"),
    CODE_IS_EMPTY(400_0023, "Code is empty"),
    FILE_IS_EMPTY(400_0023, "File is empty"),
    FILE_UPLOAD_FAILED(400_011, "File upload failed"),
    FILE_IS_ERROR(400_012, "File is error"),
    NOT_FOUND_EXAMSESSION(400_021, "Not Found Exam Session"),
    ;

    private final int errorCode;

    private final String message;
}
