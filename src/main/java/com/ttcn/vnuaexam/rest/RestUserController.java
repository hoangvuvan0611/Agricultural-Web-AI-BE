package com.ttcn.vnuaexam.rest;

import com.ttcn.vnuaexam.constant.enums.Role;
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
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class RestUserController {
    private final UserService userService;

    // tạo 1 sinh viên
    @PostMapping("/add")
    public EMResponse<UserResponseDto> addUser(@RequestBody UserRequestDto requestDto) throws EMException {
        return new EMResponse<>(userService.addUser(requestDto));
    }

    // Cập nhật thông tin sinh viên
    @PutMapping("/{id}")
    public EMResponse<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto requestDto) throws EMException {
        return new EMResponse<>(userService.updateUser(id, requestDto));
    }

    // Hiển thị thông tin chi tiết user
    @GetMapping("/{id}")
    public EMResponse<UserResponseDto> getById(@PathVariable Long id) throws EMException {
        return new EMResponse<>(userService.getUserById(id));
    }

    @DeleteMapping("/{id}")
    public EMResponse<Boolean> deleteUser(@PathVariable Long id) throws EMException {
        return new EMResponse<>(userService.deleteUser(id));
    }

    // lấy thông tin user đang đăng nhập
    @GetMapping("/current")
    public EMResponse<UserClientDto> getCurrent() throws EMException {
        return new EMResponse<>(userService.getCurrentUser());
    }

    // lấy ra Page sinh viên, hiển thị danh sách sinh viên
    @GetMapping("/search-student")
    public EMResponse<Page<UserResponseDto>> searchStudent(UserSearchDto dto) {
        return new EMResponse<>(userService.searchStudent(dto));
    }

    // lấy danh sách user giảng viên, giám thị
    @GetMapping("/search-user-list")
    public EMResponse<List<UserResponseDto>> searchUserList(UserSearchDto dto) {
        return new EMResponse<>(userService.searchUserList(dto));
    }

    // lấy danh sách role user
    @GetMapping("/get-role-map")
    public EMResponse<Map<Integer, String>> getRole() {
        return new EMResponse<>(Role.getRoleMap());
    }
}
