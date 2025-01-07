package com.ttcn.vnuaexam.repository;

import com.ttcn.vnuaexam.corollary.ExamResultSetResponse;
import com.ttcn.vnuaexam.dto.request.ExamRoomRequestDto;
import com.ttcn.vnuaexam.dto.search.ExamSearchDto;
import com.ttcn.vnuaexam.entity.Exam;
import com.ttcn.vnuaexam.entity.ExamRoom;
import com.ttcn.vnuaexam.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRoomRepository extends JpaRepository<ExamRoom, Long> {
}
