package com.ttcn.vnuaexam.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tbl_answer")
public class Exam {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "subject_id" , referencedColumnName = "id")
    private Subject subject;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private int duration;

    @Column(name = "total_questions")
    private int totalQuestions;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "modify_date")
    private Date modifyDate;

    @Column(name = "exam_date")
    private Date examDate;
}
