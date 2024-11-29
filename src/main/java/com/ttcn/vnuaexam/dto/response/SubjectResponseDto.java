package com.ttcn.vnuaexam.dto.response;

import com.ttcn.vnuaexam.corollary.SubjectResultSetResponse;
import com.ttcn.vnuaexam.dto.BaseObjectDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectResponseDto extends BaseObjectDto {
    private Long id;
    private String code;
    private String name;
    private String description;

    public SubjectResponseDto(SubjectResultSetResponse resultSet) {
        this.id = resultSet.getId();
        this.code = resultSet.getCode();
        this.name = resultSet.getName();
        this.description = resultSet.getDescription();
    }
}
