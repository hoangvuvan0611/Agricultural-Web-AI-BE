package com.ttcn.vnuaexam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tbl_exam")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Exam extends BaseEntity {
    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "code")
    private String code;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "total_questions")
    private Integer totalQuestions;

    @Column(name = "total_score")
    private BigDecimal totalScore;

    @Column(name = "exam_date")
    private LocalDateTime examDate;

    @Column(name = "status")
    private Integer status;

    @Column(name = "had_question")
    private Integer hadQuestion;
}
