package com.ttcn.vnuaexam.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class RoomStudentResponseDto {
    private Long id;
    private Long examRoomId;
    private Long studentId;
    private Integer status;
    private BigDecimal score;
    private LocalDateTime submitTime;
}
