package com.ttcn.vnuaexam.dto.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectSearchDto extends SearchDto{
    private Long departmentId;
    private String code;
    private String name;
    private String description;
}
