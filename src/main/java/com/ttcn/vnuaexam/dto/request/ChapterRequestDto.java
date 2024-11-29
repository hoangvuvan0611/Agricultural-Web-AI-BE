package com.ttcn.vnuaexam.dto.request;

import com.ttcn.vnuaexam.dto.BaseObjectDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChapterRequestDto extends BaseObjectDto {
    private Long id;
    private Long subjectId;
    private String name;
    private String description;
}
