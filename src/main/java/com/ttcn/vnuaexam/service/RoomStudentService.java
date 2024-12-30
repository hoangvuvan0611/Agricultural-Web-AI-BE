package com.ttcn.vnuaexam.service;

import java.util.List;

public interface RoomStudentService {
    String addStudentToRoom(List<String> listStudentCode, Long roomId);
}
