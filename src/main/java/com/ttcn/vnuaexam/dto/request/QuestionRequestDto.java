package com.ttcn.vnuaexam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequestDto {
    private String id;
    private String chapterId;
    private Date createDate;
    private String createdBy;
    private Date modifyDate;
    private String modifiedBy;
    private String content;
    private String image;
    private String type;
}
