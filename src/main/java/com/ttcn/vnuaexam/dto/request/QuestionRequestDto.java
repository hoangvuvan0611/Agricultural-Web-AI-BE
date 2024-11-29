package com.ttcn.vnuaexam.dto.request;

import com.ttcn.vnuaexam.dto.BaseObjectDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequestDto extends BaseObjectDto {
    private Long id;
    private Long subjectId;
    private Long chapterId;
    private String content;
    private String image;
    private Integer countCorrect;
    List<AnswerRequestDto> answers;
}
