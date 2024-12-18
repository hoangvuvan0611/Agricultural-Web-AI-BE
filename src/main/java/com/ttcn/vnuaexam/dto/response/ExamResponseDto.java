package com.ttcn.vnuaexam.dto.response;

import com.ttcn.vnuaexam.corollary.ExamResultSetResponse;
import com.ttcn.vnuaexam.dto.BaseObjectDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamResponseDto extends BaseObjectDto {
    private Long id;
    private Long subjectId;
    private String code;
    private String title;
    private String description;
    private Integer duration;
    private Integer totalQuestions;
    private BigDecimal totalScore;
    private LocalDateTime examDate;
    private Integer status;
    private Integer hadQuestion;
    private List<QuestionResponseDto> questions;

    public ExamResponseDto(ExamResultSetResponse response) {
        this.id = response.getId();
        this.title = response.getTitle();
        this.description = response.getDescription();
        this.duration = response.getDuration();
        this.totalQuestions = response.getTotalQuestions();
        this.totalScore = response.getTotalScore();
        this.status = response.getStatus();
        this.hadQuestion = response.getHadQuestion();
    }
}
