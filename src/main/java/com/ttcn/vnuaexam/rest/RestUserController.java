package com.ttcn.vnuaexam.rest;

import com.ttcn.vnuaexam.dto.request.UserRequestDto;
import com.ttcn.vnuaexam.dto.response.UserResponseDto;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.response.EMResponse;
import com.ttcn.vnuaexam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class RestUserController {
    private final UserService userService;

    @PostMapping("/add")
    public EMResponse<UserResponseDto> addUser(@RequestBody UserRequestDto requestDto) throws EMException {
        return new EMResponse<>(userService.addUser(requestDto));
    }

    @PutMapping("/{id}")
    public EMResponse<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto requestDto) throws EMException {
        return new EMResponse<>(userService.updateUser(id, requestDto));
    }

    @GetMapping("/{code}")
    public EMResponse<UserResponseDto> getUser(@PathVariable Long id) throws EMException {
        return new EMResponse<>(userService.getUserById(id));
    }

    @DeleteMapping("/{id}")
    public EMResponse<Boolean> deleteUser(@PathVariable Long id) throws EMException {
        return new EMResponse<>(userService.deleteUser(id));
    }

    @GetMapping("/all")
    public EMResponse<List<UserResponseDto>> getAllUser() throws EMException {
        return new EMResponse<>(userService.getAllUsers());
    }
}
