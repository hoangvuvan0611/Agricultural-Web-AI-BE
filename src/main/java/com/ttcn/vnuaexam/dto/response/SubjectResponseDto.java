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
    private Integer numberOfChapters;
    private Integer numberOfQuestions;
    private Integer numberOfExams;
    private List<ChapterResponseDto> chapters;

    public SubjectResponseDto(Long id, String code, String name, String description, Long numberOfChapters,
                              Long numberOfQuestions, Long numberOfExams) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.numberOfChapters = numberOfChapters.intValue();
        this.numberOfQuestions = numberOfQuestions.intValue();
        this.numberOfExams = numberOfExams.intValue();
    }
}
