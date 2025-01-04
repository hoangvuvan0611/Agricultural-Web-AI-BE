package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.constant.enums.Role;
import com.ttcn.vnuaexam.dto.MessageDataDTO;
import com.ttcn.vnuaexam.dto.client.UserClientDto;
import com.ttcn.vnuaexam.dto.request.UserRequestDto;
import com.ttcn.vnuaexam.dto.response.UserResponseDto;
import com.ttcn.vnuaexam.dto.search.SearchDto;
import com.ttcn.vnuaexam.dto.search.UserSearchDto;
import com.ttcn.vnuaexam.entity.User;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.repository.UserRepository;
import com.ttcn.vnuaexam.service.UserService;
import com.ttcn.vnuaexam.service.mapper.UserMapper;
import com.ttcn.vnuaexam.utils.PageUtils;
import lombok.AllArgsConstructor;
import org.mapstruct.control.MappingControl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ttcn.vnuaexam.constant.MessageCodes.ImportStudent.CODE_STUDENT_DUPLICATE;
import static com.ttcn.vnuaexam.constant.enums.ErrorCodeEnum.*;
import static com.ttcn.vnuaexam.constant.enums.Role.STUDENT;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    private void validateUser(UserRequestDto userRequestDto, boolean isCreate) throws EMException {
        //Kiểm tra code có trống
        if (!StringUtils.hasText(userRequestDto.getCode())) {
            throw new EMException(CODE_IS_EMPTY);
        }

        //Kiểm tra có trùng code
        Optional<User> usersWithCode;
        if (isCreate) {
            usersWithCode = userRepository.findByCode(userRequestDto.getCode());
        } else {
            usersWithCode = userRepository.findByCodeAndIdNot(userRequestDto.getCode(), userRequestDto.getId());
        }

        if (usersWithCode.isPresent()) {
            throw new EMException(USER_CODE_ALREADY_EXISTS);
        }

        //Kiểm tra username có trống
        if (!StringUtils.hasText(userRequestDto.getUsername())) {
            throw new EMException(USER_NAME_NOT_EMPTY);
        }
        // kiểm tra có trùng username
        Optional<User> usersWithUsername;
        if (isCreate) {
            usersWithUsername = userRepository.findByUsername(userRequestDto.getUsername());

        } else {
            usersWithUsername = userRepository.findByUsernameAndIdNot(userRequestDto.getUsername(), userRequestDto.getId());
        }

        if (usersWithUsername.isPresent()) {
            throw new EMException(USER_NAME_ALREADY_EXISTS);
        }

    }

    private String validateUserRequest(UserRequestDto userRequestDto, boolean isCreate) {
        String errorMessage = "";
        //Kiểm tra có trùng code
        Optional<User> usersWithCode;
        if (isCreate) {
            usersWithCode = userRepository.findByCode(userRequestDto.getCode());
        } else {
            usersWithCode = userRepository.findByCodeAndIdNot(userRequestDto.getCode(), userRequestDto.getId());
        }

        if (usersWithCode.isPresent()) {
            errorMessage = String.format(CODE_STUDENT_DUPLICATE, userRequestDto.getCode());
        }

        return errorMessage;
    }

    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) throws EMException {
        validateUser(userRequestDto, true);
        var user = userMapper.requestToEntity(userRequestDto);
        if (userRequestDto.getPassword() != null) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
            user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        }
        String addedBy = getCurrentUserName();
        user.setCreatedBy(addedBy);
        userRepository.save(user);
        return userMapper.entityToResponse(user);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN') or @userSecurity.isCurrentUser(#id)")
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) throws EMException {
        User user = userRepository.findById(id).orElseThrow(() -> new EMException(NOT_FOUND_USER));

        validateUser(userRequestDto, false);
        userMapper.setValue(userRequestDto, user);
        if (userRequestDto.getPassword() != null) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
            user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        }
        String updatedBy = getCurrentUserName();
        user.setModifiedBy(updatedBy);
        userRepository.save(user);
        return userMapper.entityToResponse(user);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN') or @userSecurity.isCurrentUser(#id)")
    public UserResponseDto getUserById(Long id) throws EMException {
        User user = userRepository.findById(id).orElseThrow(() -> new EMException(NOT_FOUND_USER));
        return userMapper.entityToResponse(user);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public boolean deleteUser(Long id) throws EMException {
        User user = userRepository.findById(id).orElseThrow(() -> new EMException(NOT_FOUND_USER));
        userRepository.delete(user);
        return true;
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(userMapper::entityToResponse)
                .collect(Collectors.toList());
    }

    private String getCurrentUserName() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null ? authentication.getName() : null;
    }

    @Override
    @PreAuthorize("isAuthenticated()")
//    @PreAuthorize("hasAnyAuthority('ADMIN', 'TEACHER', 'PROCTOR', 'STUDENT')")
    public UserClientDto getCurrentUser() throws EMException {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var username = authentication != null ? authentication.getName() : null;
        UserResponseDto userResponseDto = userRepository.findByUsername(username).map(userMapper::entityToResponse)
                .orElseThrow(() -> new EMException(NOT_FOUND_USER));

        return UserClientDto.builder()
                .id(userResponseDto.getId())
                .username(username)
                .fullName(userResponseDto.getFullName())
                .code(userResponseDto.getCode())
                .role(userResponseDto.getRole())
                .build();
    }

    public String importListStudent(List<UserRequestDto> requestDtoList) {
        StringBuilder message = new StringBuilder();
        String errorMessage;
        for (UserRequestDto dto : requestDtoList) {
            errorMessage = validateUserRequest(dto, true);
            if (StringUtils.hasText(errorMessage)) {
                message.append(errorMessage);
                continue;
            }

            dto.setUsername(dto.getCode());
            dto.setRole(STUDENT.getNumRole());
            var userEntity = userMapper.requestToEntity(dto);
            userRepository.save(userEntity);
        }
        return message.toString();
    }

    @Override
    public Page<UserResponseDto> search(SearchDto dto) {
        Pageable pageRequest = PageUtils.getPageable(dto.getPageIndex(), dto.getPageSize());
        Page<User> usersEntity = userRepository.search(dto, pageRequest);
        return usersEntity.map(userMapper::entityToResponse);
    }
}
