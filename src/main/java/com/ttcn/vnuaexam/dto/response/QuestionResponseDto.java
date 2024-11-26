package com.ttcn.vnuaexam.dto.response;

import com.ttcn.vnuaexam.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class QuestionResponseDto extends BaseEntity {
    private Long id;
    private String code;
    private String content;
    private String image;
    private String type;
    private Long subjectId;
    List<AnswerResponseDto> answers;
}
