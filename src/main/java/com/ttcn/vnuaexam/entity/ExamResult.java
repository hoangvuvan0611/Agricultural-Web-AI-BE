package com.ttcn.vnuaexam.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tbl_exam_result")
public class ExamResult {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "session_id", referencedColumnName = "id")
    private ExamSession examSession;

    @Column(name = "total_score")
    private double totalScore;

    @Column(name = "correct_count")
    private int correctCount;

    @Column(name = "wrong_count")
    private int wrongCount;

    @Column(name = "un_answer_count")
    private int unAnswerCount;
}
