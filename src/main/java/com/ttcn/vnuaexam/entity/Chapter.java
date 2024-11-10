package com.ttcn.vnuaexam.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tbl_chapter")
public class Chapter {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;
}
