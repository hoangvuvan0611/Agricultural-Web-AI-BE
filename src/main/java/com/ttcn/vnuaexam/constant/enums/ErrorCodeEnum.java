package com.ttcn.vnuaexam.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {
    SUCCESS(200, "Success"),
    BAD_REQUEST(400, "Bad Request"),
    NOT_FOUND(404, "Not Found"),
    DEPARTMENT_CODE_OR_NAME_IS_EMPTY(400_001, "Department code or name is empty"),
    DEPARTMENT_CODE_IS_EXIST(400_002, "Department code is exist"),
    DEPARTMENT_ID_IS_NOT_EXIST(400_003, "Department id is not exist"),
    SUBJECT_ID_IS_NOT_EXIST(400_004, "Subject id is not exist"),
    SUBJECT_NAME_OR_CODE_IS_EMPTY(400_005, "Subject name or code is empty"),
    SUBJECT_CODE_IS_EXIST(400_006, "Subject code is exist"),
    NOT_FOUND_DEPARTMENT(400_007, "Not Found Department"),
    NOT_FOUND_SUBJECT(400_008, "Not Found Subject"),
    QUESTION_ID_IS_NOT_EXIST(400_009, "Question id is not exist"),
    NOT_FOUND_QUESTION(400_0010, "Not Found Question"),
    QUESTION_CODE_IS_EXIST(400_0011, "Question code is exist"),
    CODE_IS_EMPTY(400_0012, "Question code is empty"),
    ANSWER_CODE_IS_EXIST(400_0014, "Answer code is exist"),
    CANNOT_CREATE_ANSWER(400_0015, "Can't create answer"),
    USER_CODE_IS_NOT_EXIST(400_0016, "User code is not exist"),
    USER_CODE_ALREADY_EXISTS(400_0017, "User code already exists"),
    NOT_FOUND_USER(400_0018, "Not Found User"),
    NOT_FOUND_USERNAME(400_0018, "Not Found User Name"),
    USER_NAME_NOT_EMPTY(400_0019, "User name is empty"),
    USER_NAME_ALREADY_EXISTS(400_0017, "User name already exists"),
    UNAUTHENTICATED(400_0018, "Unauthenticated"),
    ;

    private final int errorCode;

    private final String message;
}
