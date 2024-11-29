package com.ttcn.vnuaexam.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tbl_question")
public class Question extends BaseEntity {
    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "chapter_id")
    private Long chapterId;

    @Column(name = "content")
    private String content;

    @Column(name = "image")
    private String image;

    @Column(name = "count_correct")
    private Integer countCorrect;
}
