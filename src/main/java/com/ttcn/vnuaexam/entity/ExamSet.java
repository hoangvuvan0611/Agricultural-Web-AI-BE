package com.ttcn.vnuaexam.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_exam_set")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamSet extends BaseEntity {
    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "code")
    private String code;

    @Column(name = "title")
    private String title;

    @Column(name = "exam_set_id")
    private Long examSetId;
}
