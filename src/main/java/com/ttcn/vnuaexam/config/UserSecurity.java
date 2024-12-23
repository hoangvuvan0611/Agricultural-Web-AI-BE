package com.ttcn.vnuaexam.config;

import com.ttcn.vnuaexam.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserSecurity {
    private final UserRepository userRepository;

    public boolean isCurrentUser(Long userId) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(currentUsername)
                .map(user -> user.getId().equals(userId))
                .orElse(false);
    }
}
