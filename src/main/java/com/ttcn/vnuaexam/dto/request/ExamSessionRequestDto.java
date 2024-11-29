package com.ttcn.vnuaexam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExamSessionRequestDto {
    private String id;
    private String examId;
    private String studentId;
    private String teacherId;
    private Date startTime;
    private Date endTime;
    private Date submitTime;
    private int status;

}
