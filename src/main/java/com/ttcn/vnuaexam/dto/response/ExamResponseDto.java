package com.ttcn.vnuaexam.dto.response;

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
}
