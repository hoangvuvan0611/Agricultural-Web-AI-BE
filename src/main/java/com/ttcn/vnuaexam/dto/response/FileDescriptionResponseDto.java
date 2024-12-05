package com.ttcn.vnuaexam.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileDescriptionResponseDto {
    private Long id;
    private int contentSize;
    private String contentType;
    private String name;
    private String filePath;
}
