package com.ttcn.vnuaexam.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SubjectResponseDto {
    private String id;
    private String name;
    private String code;
    private String description;
    private String departmentId;
}
