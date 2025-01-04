package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.entity.RoomStudent;
import com.ttcn.vnuaexam.entity.User;
import com.ttcn.vnuaexam.repository.RoomStudentRepository;
import com.ttcn.vnuaexam.repository.UserRepository;
import com.ttcn.vnuaexam.service.RoomStudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.ttcn.vnuaexam.constant.MessageCodes.ImportStudent.*;

@Service
@AllArgsConstructor
public class RoomStudentServiceImpl implements RoomStudentService {
    private final RoomStudentRepository roomStudentRepository;
    private final UserRepository userRepository;

    @Override
    public String addStudentToRoom(List<String> listStudentCode, Long roomId) {
        return listStudentCode.stream()
                .map(code -> userRepository.findByCode(code)
                        .map(user -> processStudent(user, code, roomId))
                        .orElse(String.format(CODE_STUDENT_IS_NOT_EXISTED, code)))
                .filter(StringUtils::hasText)
                .collect(Collectors.joining());
    }
    private String processStudent(User user, String code, Long roomId) {
        if (roomStudentRepository.existsByStudentIdAndExamRoomId(user.getId(), roomId)) {
            return String.format(CODE_STUDENT_JOINED, code);
        }

        var entity = new RoomStudent();
        entity.setStudentId(user.getId());
        entity.setExamRoomId(roomId);
        roomStudentRepository.save(entity);
        return "";
    }
}
