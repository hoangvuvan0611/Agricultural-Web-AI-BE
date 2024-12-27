package com.ttcn.vnuaexam.rest;

import com.ttcn.vnuaexam.dto.client.UserClientDto;
import com.ttcn.vnuaexam.dto.request.UserRequestDto;
import com.ttcn.vnuaexam.dto.response.UserResponseDto;
import com.ttcn.vnuaexam.dto.search.UserSearchDto;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.response.EMResponse;
import com.ttcn.vnuaexam.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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

    @GetMapping("/{id}")
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

    @GetMapping("/current")
    public EMResponse<UserClientDto> getCurrent() throws EMException {
        return new EMResponse<>(userService.getCurrentUser());
    }

    @GetMapping("/search")
    public EMResponse<Page<UserResponseDto>> search(UserSearchDto dto) {
        return new EMResponse<>(userService.search(dto));
    }

}
