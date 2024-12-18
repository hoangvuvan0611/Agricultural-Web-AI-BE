package com.ttcn.vnuaexam.dto.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamSearchDto extends SearchDto{
    private Long subjectId;
    private String subjectName;
    private Long userId;
    private String name;
    private String description;
}
