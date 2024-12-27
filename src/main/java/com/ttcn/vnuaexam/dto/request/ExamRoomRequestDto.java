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
public class ExamRoomRequestDto {
    private Long id;
    private Long examId;
    private Long teacherId;
    private String code;
    private Date startTime;
    private Date endTime;
    private Date sessionDate;
    private int status;

}
