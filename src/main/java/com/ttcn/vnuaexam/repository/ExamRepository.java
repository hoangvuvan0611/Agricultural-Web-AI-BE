package com.ttcn.vnuaexam.repository;

import com.ttcn.vnuaexam.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExamRepository extends JpaRepository<Exam, UUID> {
}
