package com.ttcn.vnuaexam.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class ExamRoomResponseDto {
    private Long id;
    private Long examId;
    private Long teacherId;
    private String code;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime sessionDate;
    private int status;
}
