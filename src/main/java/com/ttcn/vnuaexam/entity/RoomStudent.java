package com.ttcn.vnuaexam.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "tbl_exam_session_student")
@NoArgsConstructor
@AllArgsConstructor
public class RoomStudent extends BaseEntity {
    @Column(name = "exam_session_id")
    private Long examRoomId;

    @Column(name ="student_id")
    private Long studentId;

    @Column(name = "status")
    private Integer status;

    @Column(name = "score")
    private BigDecimal score;

    @Column(name = "submit_time")
    private LocalDateTime submitTime;
}
