package com.ttcn.vnuaexam.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CollectionId;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tbl_answer")
public class Answer extends BaseEntity {
    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "order_number")
    private Integer orderNumber;

    @Column(name = "content")
    private String content;

    @Column(name = "image")
    private String image;

    @Column(name = "is_correct")
    private Boolean isCorrect;
}
