package com.ttcn.vnuaexam.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tbl_exam_session")
public class ExamRoom extends BaseEntity {
   @Column(name = "exam_id")
    private Long examId;

    @Column(name ="teacher_id")
    private Long teacherId;

    @Column(name = "code")
    private String code;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "session_date")
    private LocalDateTime sessionDate;

    @Column(name = "status")
    private int status;
}
