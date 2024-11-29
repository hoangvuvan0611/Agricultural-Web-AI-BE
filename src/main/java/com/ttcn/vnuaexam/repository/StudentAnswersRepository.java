package com.ttcn.vnuaexam.repository;

import com.ttcn.vnuaexam.entity.StudentAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentAnswersRepository extends JpaRepository<StudentAnswers, UUID> {
}
