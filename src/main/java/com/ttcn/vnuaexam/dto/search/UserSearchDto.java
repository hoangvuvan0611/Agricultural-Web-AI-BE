package com.ttcn.vnuaexam.dto.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChapterSearchDto extends SearchDto{
    private Long subjectId;
    private String name;
    private String description;
}
