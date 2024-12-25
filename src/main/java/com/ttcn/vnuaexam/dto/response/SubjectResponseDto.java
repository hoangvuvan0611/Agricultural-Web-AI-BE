package com.ttcn.vnuaexam.dto.response;

import com.ttcn.vnuaexam.corollary.SubjectResultSetResponse;
import com.ttcn.vnuaexam.dto.BaseObjectDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectResponseDto extends BaseObjectDto {
    private Long id;
    private String code;
    private String name;
    private String description;
    private List<ChapterResponseDto> chapters;
}
