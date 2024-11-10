package com.ttcn.vnuaexam.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tbl_question")
public class Question {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "chapter_id", referencedColumnName = "id")
    private Chapter chapter;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modify_date")
    private Date modifyDate;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "content")
    private String content;

    @Column(name = "image")
    private String image;

    @Column(name = "type")
    private String type;

}
