package com.ttcn.vnuaexam.dto.response;

import com.ttcn.vnuaexam.dto.BaseObjectDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSubjectResponseDto extends BaseObjectDto {
    private Long userId;
    private Long subjectId;
}
