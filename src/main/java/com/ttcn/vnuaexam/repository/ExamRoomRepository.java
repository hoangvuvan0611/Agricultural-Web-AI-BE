package com.ttcn.vnuaexam.repository;

import com.ttcn.vnuaexam.dto.response.ExamRoomResponseDto;
import com.ttcn.vnuaexam.dto.search.ExamRoomSearchDto;
import com.ttcn.vnuaexam.entity.ExamRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRoomRepository extends JpaRepository<ExamRoom, Long> {
    @Query("SELECT new com.ttcn.vnuaexam.dto.response.ExamRoomResponseDto( " +
            "r.id, " +
            "r.examId, " +
            "r.teacherId, " +
            "r.room, " +
            "r.startTime, " +
            "r.endTime, " +
            "r.status ) " +
            "FROM ExamRoom r " +
            "WHERE (:#{#dto.userId} is null OR r.teacherId = :#{#dto.userId})")
    Page<ExamRoomResponseDto> search(@Param("dto") ExamRoomSearchDto dto, Pageable pageable);
}
