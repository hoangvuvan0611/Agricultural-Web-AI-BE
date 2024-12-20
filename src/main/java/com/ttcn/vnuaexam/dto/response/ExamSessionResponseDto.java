package com.ttcn.vnuaexam.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ExamSessionResponseDto {
    private Long id;
    private Long examId;
    private Long teacherId;
    private String code;
    private Date startTime;
    private Date endTime;
    private Date sessionDate;
    private int status;
}
