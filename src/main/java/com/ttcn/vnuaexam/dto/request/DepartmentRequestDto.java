package com.ttcn.vnuaexam.dto.request;

import com.ttcn.vnuaexam.constant.MessageCodes;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequestDto {
    private Long id;
    @NotBlank(message = MessageCodes.NOT_BLANK)
    @Size(max = 255, message = MessageCodes.NOT_TOO_LONG)
    private String code;
    @NotBlank(message = MessageCodes.NOT_BLANK)
    private String name;
    private String description;
}
