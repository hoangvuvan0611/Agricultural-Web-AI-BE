package com.ttcn.vnuaexam.dto.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamRoomSearchDto extends SearchDto{
    private Long subjectId;
    private Long userId;
}
