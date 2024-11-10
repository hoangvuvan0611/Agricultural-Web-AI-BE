package com.ttcn.vnuaexam.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tbl_answer")
public class Answer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question question;

    @Column(name = "content")
    private String content;

    @Column(name = "is_correct")
    private Boolean isCorrect;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modify_date")
    private Date modifyDate;

    @Column(name = "modified_by")
    private String modifiedBy;
}
