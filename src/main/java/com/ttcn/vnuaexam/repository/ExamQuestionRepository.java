package com.ttcn.vnuaexam.repository;

import com.ttcn.vnuaexam.entity.ExamQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExamQuestionRepository extends JpaRepository<ExamQuestion, Long> {
    List<ExamQuestion> findByExamId(Long examId);

    void deleteByQuestionId(Long questionId);

    @Query(value = "DELETE from ExamQuestion eq WHERE eq.examId = :id")
    @Modifying
    void deleteByExamId(@Param("id") Long examId);
}
