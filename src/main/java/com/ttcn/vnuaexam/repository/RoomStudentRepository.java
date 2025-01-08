package com.ttcn.vnuaexam.repository;

import com.ttcn.vnuaexam.entity.ExamRoom;
import com.ttcn.vnuaexam.entity.RoomStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomStudentRepository extends JpaRepository<RoomStudent, Long> {
    boolean existsByStudentIdAndExamRoomId(Long studentId, Long examRoomId);

    ExamRoom findByStudentIdAndStatus(Long studentId, Integer status);
}
