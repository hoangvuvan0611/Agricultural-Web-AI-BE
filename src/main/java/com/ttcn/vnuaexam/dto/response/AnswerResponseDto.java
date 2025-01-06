package com.ttcn.vnuaexam.dto.response;

import com.ttcn.vnuaexam.dto.BaseObjectDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResponseDto extends BaseObjectDto implements Serializable {
    private Long id;
    private Long questionId;
    private Integer orderNumber;
    private Boolean isCorrect;
    private String content;
    private String image;
    private Integer type;
}
