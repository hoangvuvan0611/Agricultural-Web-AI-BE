package com.ttcn.vnuaexam.dto.response;

import com.ttcn.vnuaexam.dto.BaseObjectDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResponseDto extends BaseObjectDto {
    private Long id;
    private Long questionId;
    private Integer orderNumber;
    private String content;
    private String image;
    private Boolean isCorrect;
}
