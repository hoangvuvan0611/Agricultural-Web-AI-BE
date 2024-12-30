package com.ttcn.vnuaexam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.Inet4Address;

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
    private Integer type;
}
