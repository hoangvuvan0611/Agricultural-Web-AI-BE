package com.ttcn.vnuaexam.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tbl_exam_question")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamQuestion extends BaseEntity {
    @Column(name = "exam_id")
    private Long examId;

    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "question_order")
    private Integer questionOrder;
}
