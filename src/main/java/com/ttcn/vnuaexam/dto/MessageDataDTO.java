package com.ttcn.vnuaexam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MessageDataDTO<T> {
    private String message;
    private T data;
}
