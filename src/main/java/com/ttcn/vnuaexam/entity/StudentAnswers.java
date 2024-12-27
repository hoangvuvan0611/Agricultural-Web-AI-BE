package com.ttcn.vnuaexam.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tbl_student_answers")
public class StudentAnswers {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "session_id",referencedColumnName = "id")
    private ExamRoom examRoom;

    @ManyToOne
    @JoinColumn(name = "question_id",referencedColumnName = "id")
    private ExamQuestion examQuestion;

    @ManyToOne
    @JoinColumn(name = "answer_id", referencedColumnName = "id")
    private Answer answer;

    @Column(name = "answer_date")
    private Date answerDate;

}
