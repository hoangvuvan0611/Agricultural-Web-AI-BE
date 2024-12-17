package com.ttcn.vnuaexam.corollary;

public interface QuestionResultSetResponse {
    Long getId();
    String getContent();
    String getImage();
    Integer getCountCorrect();
    String getDescription();
    Long getDepartmentId();
}
