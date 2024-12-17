package com.ttcn.vnuaexam.repository;

import com.ttcn.vnuaexam.entity.Exam;
import com.ttcn.vnuaexam.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    @Query(value = "SELECT q.id " +
            " FROM tbl_exam_question exq " +
            " LEFT JOIN tbl_exam ex ON ex.id = exq.exam_id " +
            " LEFT JOIN tbl_question q ON q.id = exq.question_id " +
            " WHERE ex.id = :examId",
    nativeQuery = true)
    List<Question> findQuestionInExam(@Param("examId") Long examId);

    void deleteBySubjectId(Long id);
}
