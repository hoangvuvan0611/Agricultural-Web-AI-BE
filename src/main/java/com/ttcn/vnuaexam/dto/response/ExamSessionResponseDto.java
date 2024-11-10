package com.ttcn.vnuaexam.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ExamSessionResponseDto {
    private String id;
    private String examId;
    private String studentId;
    private String teacherId;
    private Date startTime;
    private Date endTime;
    private Date submitTime;
    private int status;
}
