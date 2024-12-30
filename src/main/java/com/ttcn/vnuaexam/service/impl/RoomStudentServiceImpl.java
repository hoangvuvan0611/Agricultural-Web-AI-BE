package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.dto.request.RoomStudentRequestDto;
import com.ttcn.vnuaexam.repository.RoomStudentRepository;
import com.ttcn.vnuaexam.repository.UserRepository;
import com.ttcn.vnuaexam.service.RoomStudentService;
import com.ttcn.vnuaexam.service.mapper.RoomStudentMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.ttcn.vnuaexam.constant.MessageCodes.ImportStudent.CODE_STUDENT_DUPLICATE;
import static com.ttcn.vnuaexam.constant.MessageCodes.ImportStudent.CODE_STUDENT_IS_NOT_EXISTED;

@Service
@AllArgsConstructor
public class RoomStudentServiceImpl implements RoomStudentService {
    private final RoomStudentRepository roomStudentRepository;
    private final RoomStudentMapper roomStudentMapper;
    private final UserRepository userRepository;

    @Override
    public String addStudentToRoom(List<String> listStudentCode, Long roomId) {
        StringBuilder message = new StringBuilder();
        String errorMessage = "";

        for (String code : listStudentCode) {
            var user = userRepository.findByCode(code);
            if (user.isEmpty()) {
                errorMessage = String.format(CODE_STUDENT_IS_NOT_EXISTED, code);
            } else if (roomStudentRepository.existsByStudentId(user.get().getId())) {
                errorMessage = String.format(CODE_STUDENT_DUPLICATE, code);
            }

            if (StringUtils.hasText(errorMessage)) {
                message.append(errorMessage);
                continue;
            }

            RoomStudentRequestDto requestDto = RoomStudentRequestDto.builder()
                    .examRoomId(roomId)
                    .studentId(user.get().getId())
                    .build();

            var entity = roomStudentMapper.requestDtoToEntity(requestDto);
            roomStudentRepository.save(entity);
        }
        return message.toString();
    }

}
