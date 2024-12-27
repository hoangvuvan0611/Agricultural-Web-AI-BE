package com.ttcn.vnuaexam.dto.response;

import com.ttcn.vnuaexam.constant.enums.StatusSemesterEnum;
import com.ttcn.vnuaexam.dto.BaseObjectDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SemesterResponseDto extends BaseObjectDto {
    private Long id;
    private String code;
    private String title;
    private LocalDate startDate;
    private LocalDate finishDate;
    private String status;

    public void setStatus(int status) {
        this.status = StatusSemesterEnum.codeToValue(status);
    }
}
