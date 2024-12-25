package com.ttcn.vnuaexam.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_user_subject")
@AllArgsConstructor
public class UserSubject extends BaseEntity {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "subject_id")
    private Long subjectId;
}
