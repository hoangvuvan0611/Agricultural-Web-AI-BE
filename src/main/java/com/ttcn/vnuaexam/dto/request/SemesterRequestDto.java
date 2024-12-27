package com.ttcn.vnuaexam.dto.request;

import com.ttcn.vnuaexam.dto.BaseObjectDto;
import com.ttcn.vnuaexam.helper.DateHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SemesterRequestDto extends BaseObjectDto {
    private Long id;
    private String code;
    private String title;
    private String startDate;
    private String finishDate;

    public LocalDate getStartDate() {
        if (startDate != null)
            return DateHelper.fromDateSlash(startDate);
        return null;
    }

    public LocalDate getFinishDate() {
        if (finishDate != null)
            return DateHelper.fromDateSlash(finishDate);
        return null;
    }
}
