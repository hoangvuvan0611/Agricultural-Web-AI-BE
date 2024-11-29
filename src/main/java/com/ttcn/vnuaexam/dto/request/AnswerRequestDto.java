package com.ttcn.vnuaexam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerRequestDto {
    private Long id;
    private Long questionId;
    private Integer orderNumber;
    private String content;
    private String image;
    private Boolean isCorrect;
}
