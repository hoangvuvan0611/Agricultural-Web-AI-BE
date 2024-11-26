package com.ttcn.vnuaexam.dto.response;

import com.ttcn.vnuaexam.corollary.SubjectResultSetResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectResponseDto {
    private Long id;
    private String name;
    private String code;
    private String description;
    private Long departmentId;

    public SubjectResponseDto(SubjectResultSetResponse resultSet) {
        this.id = resultSet.getId();
        this.code = resultSet.getCode();
        this.name = resultSet.getName();
        this.description = resultSet.getDescription();
        this.departmentId = resultSet.getDepartmentId();
    }
}
