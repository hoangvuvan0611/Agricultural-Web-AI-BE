package com.ttcn.vnuaexam.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_semester")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Semester extends BaseEntity {
    @Column(name = "code")
    private String code;

    @Column(name = "title")
    private String title;

    @Column(name = "begin_time")
    private LocalDate startDate;

    @Column(name = "finish_time")
    private LocalDate finishDate;

    @Column(name = "status")
    private int status;
}
