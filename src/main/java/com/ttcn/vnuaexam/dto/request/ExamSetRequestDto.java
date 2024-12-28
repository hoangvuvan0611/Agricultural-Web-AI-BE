package com.ttcn.vnuaexam.dto.request;

import com.ttcn.vnuaexam.dto.BaseObjectDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExamSetRequestDto extends BaseObjectDto {
    private Long id;
    private Long subjectId;
    private Long examSetId;
    private String code;
    private String title;
}
