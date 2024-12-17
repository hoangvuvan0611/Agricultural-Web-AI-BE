package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.dto.request.UserRequestDto;
import com.ttcn.vnuaexam.dto.response.UserResponseDto;
import com.ttcn.vnuaexam.entity.User;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.repository.UserRepository;
import com.ttcn.vnuaexam.service.UserService;
import com.ttcn.vnuaexam.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ttcn.vnuaexam.constant.enums.ErrorCodeEnum.*;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    private void validateUser(UserRequestDto userRequestDto, boolean isCreate) throws EMException {
        //Kiểm tra code có trống
        if (!StringUtils.hasText(userRequestDto.getCode())) {
            throw new EMException(CODE_IS_EMPTY, e);
        }

        //Kiểm tra có trùng code
        Optional<User> usersWithCode;
        if (isCreate) {
            usersWithCode = userRepository.findByCode(userRequestDto.getCode());

        } else {
            usersWithCode = userRepository.findByCodeAndIdNot(userRequestDto.getCode(), userRequestDto.getId());
        }

        if (usersWithCode.isPresent()) {
            throw new EMException(USER_CODE_ALREADY_EXISTS, e);
        }

        //Kiểm tra usernam có trống
        if (!StringUtils.hasText(userRequestDto.getUsername())) {
            throw new EMException(USER_NAME_NOT_EMPTY, e);
        }
        //kieemr tra có trùng username
        Optional<User> usersWithUsername;
        if (isCreate) {
            usersWithUsername = userRepository.findByUsername(userRequestDto.getUsername());

        } else {
            usersWithUsername = userRepository.findByUsernameAndIdNot(userRequestDto.getUsername(), userRequestDto.getId());
        }

        if (usersWithUsername.isPresent()) {
            throw new EMException(USER_NAME_ALREADY_EXISTS, e);
        }

    }

    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) throws EMException {

        validateUser(userRequestDto, true);
        var userResponseDto = userMapper.requestToEntity(userRequestDto);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        userResponseDto.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        String addedBy = getCurrentUserName();
        userResponseDto.setCreatedBy(addedBy);
        userRepository.save(userResponseDto);
        return userMapper.entityToResponse(userResponseDto);
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) throws EMException {
        User user = userRepository.findById(id).orElseThrow(() -> new EMException(NOT_FOUND_USER, e));

        validateUser(userRequestDto, false);
        userMapper.setValue(userRequestDto, user);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        String updatedBy = getCurrentUserName();
        user.setModifiedBy(updatedBy);
        userRepository.save(user);
        return userMapper.entityToResponse(user);
    }

    @Override
    public UserResponseDto getUserById(Long id) throws EMException {
        User user = userRepository.findById(id).orElseThrow(() -> new EMException(NOT_FOUND_USER, e));
        return userMapper.entityToResponse(user);
    }

    @Override
    public boolean deleteUser(Long id) throws EMException {
        User user = userRepository.findById(id).orElseThrow(() -> new EMException(NOT_FOUND_USER, e));
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
}
