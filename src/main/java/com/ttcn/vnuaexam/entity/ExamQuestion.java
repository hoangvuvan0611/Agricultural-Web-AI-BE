package com.ttcn.vnuaexam.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tbl_exam_question")
public class ExamQuestion {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "exam_id", referencedColumnName = "id")
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question question;

    @Column(name = "score")
    private double score;

    @Column(name = "order_number")
    private int orderNumber;
}
