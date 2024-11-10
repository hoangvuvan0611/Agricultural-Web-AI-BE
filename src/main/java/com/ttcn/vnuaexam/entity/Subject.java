package com.ttcn.vnuaexam.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tbl_subject")
public class Subject {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "department_id" ,referencedColumnName = "id")
    private Department department;
}
