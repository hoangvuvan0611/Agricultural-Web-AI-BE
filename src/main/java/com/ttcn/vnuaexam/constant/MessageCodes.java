package com.ttcn.vnuaexam.constant;

public class MessageCodes {
    public static final String NOT_NULL = "errors.not_null";
    public static final String NOT_BLANK = "errors.not_blank";
    public static final String NOT_TOO_LONG = "errors.not_too_long";
    public static final String NOT_WHITESPACE = "errors.not_whitespace";

    public static final String EXCEL_EXTENSION_ERROR_MESSAGE = "Vui lòng upload file excel có định dạng .xls hoăc .xlsx.";

    public static class ImportStudent {
        public static final String CODE_IS_BLANK = "Mã số sinh viên không được để trông";
        public static final String CODE_STUDENT_DUPLICATE = "Mã sinh viên %s đã tồn tại";
    }
}
