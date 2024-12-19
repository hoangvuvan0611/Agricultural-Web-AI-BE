package com.ttcn.vnuaexam.corollary;

import java.math.BigDecimal;

public interface ExamResultSetResponse {
    Long getId();
    String getTitle();
    String getDescription();
    Integer getDuration();
    Integer getTotalQuestions();
    BigDecimal getTotalScore();
    Integer getStatus();
    Integer getHadQuestion();
}
