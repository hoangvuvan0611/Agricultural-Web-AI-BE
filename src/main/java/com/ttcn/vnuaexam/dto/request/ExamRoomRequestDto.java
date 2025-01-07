package com.ttcn.vnuaexam.dto.request;

import com.ttcn.vnuaexam.helper.DateHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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
    private String room;
    private String startTime;
    private String endTime;
    private String sessionDate;
    private Integer status;

    public LocalDateTime getStartTime() {
        if (startTime != null)
            return DateHelper.fromDateTime(startTime);
        return null;
    }

    public LocalDateTime getEndTime() {
        if (endTime != null)
            return DateHelper.fromDateTime(endTime);
        return null;
    }
}
