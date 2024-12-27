package com.ttcn.vnuaexam.dto.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionSearchDto extends SearchDto{
    private String code;
    private String content;
    private Long subjectId;
    private Long chapterId;
    private Long examId;
}
