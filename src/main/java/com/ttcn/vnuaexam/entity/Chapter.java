package com.ttcn.vnuaexam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tbl_chapter")
@NoArgsConstructor
@AllArgsConstructor
public class Chapter extends BaseEntity {
    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}


