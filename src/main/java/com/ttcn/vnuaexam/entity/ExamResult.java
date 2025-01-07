package com.ttcn.vnuaexam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tbl_exam_result")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamResult extends BaseEntity {
    @Column(name = "exam_id")
    private Long examId;

    @Column(name = "exam_session_id")
    private Long examRoomId;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "total_score")
    private double totalScore;

    @Column(name = "correct_count")
    private int correctCount;

    @Column(name = "wrong_count")
    private int wrongCount;

    @Column(name = "un_answer_count")
    private int unAnswerCount;
}
