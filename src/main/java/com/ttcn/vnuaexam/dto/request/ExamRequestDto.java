package com.ttcn.vnuaexam.dto.request;

import com.ttcn.vnuaexam.dto.BaseObjectDto;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExamRequestDto extends BaseObjectDto {
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
    private List<Long> questionIds;
}
