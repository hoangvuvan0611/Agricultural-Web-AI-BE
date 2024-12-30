package com.ttcn.vnuaexam.dto.request;

import com.ttcn.vnuaexam.dto.BaseObjectDto;
import com.ttcn.vnuaexam.helper.DateHelper;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomStudentRequestDto extends BaseObjectDto {
    private Long id;
    private Long examRoomId;
    private Long studentId;
    private Integer status;
    private BigDecimal score;
    private String submitTime;

    public LocalDateTime getSubmitTime() {
        if (submitTime != null)
            return DateHelper.fromDateTime(submitTime);
        return null;
    }
}
