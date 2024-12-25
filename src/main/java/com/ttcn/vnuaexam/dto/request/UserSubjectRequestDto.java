package com.ttcn.vnuaexam.dto.request;

import com.ttcn.vnuaexam.dto.BaseObjectDto;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSubjectRequestDto extends BaseObjectDto {
    private Long userId;
    private Long subjectId;
}
