package com.ttcn.vnuaexam.repository;

import com.ttcn.vnuaexam.entity.ExamRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRoomRepository extends JpaRepository<ExamRoom, Long> {
}
