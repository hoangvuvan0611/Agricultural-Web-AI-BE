package com.ttcn.vnuaexam.repository;

import com.ttcn.vnuaexam.entity.RoomStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomStudentRepository extends JpaRepository<RoomStudent, Long> {
    boolean existsByStudentIdAndExamRoomId(Long studentId, Long examRoomId);
}
