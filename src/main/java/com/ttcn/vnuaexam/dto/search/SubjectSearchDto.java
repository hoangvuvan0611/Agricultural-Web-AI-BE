package com.ttcn.vnuaexam.dto.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectSearchDto extends SearchDto{
    private String code;
    private String name;
    private String description;
    private Long userId;
}
