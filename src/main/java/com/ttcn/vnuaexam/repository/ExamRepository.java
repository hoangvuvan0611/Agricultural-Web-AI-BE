package com.ttcn.vnuaexam.repository;

import com.ttcn.vnuaexam.corollary.ExamResultSetResponse;
import com.ttcn.vnuaexam.dto.search.ExamSearchDto;
import com.ttcn.vnuaexam.entity.Exam;
import com.ttcn.vnuaexam.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query(value = " SELECT e.id as id, " +
            " e.title as title, " +
            " e.description as description, " +
            " e.duration as duration, " +
            " e.total_questions as totalQuestions, " +
            " e.total_score as totalScore, " +
            " e.status as status, " +
            " e.had_question as hadQuestion, " +
            " s.name as subjectName " +
            " FROM tbl_exam e " +
            " LEFT JOIN tbl_subject s ON e.subject_id = s.id " +
            " WHERE (dto.subjectId is null OR e.subject_id = dto.subjectId) " +
            " AND (dto.keyword is null OR dto.keyword = '' " +
            " OR e.title LIKE CONCAT('%', dto.keyword, '%') " +
            " OR e.description LIKE CONCAT('%', dto.keyword, '%')) ", nativeQuery = true)
    Page<ExamResultSetResponse> search(@Param("dto") ExamSearchDto dto, Pageable pageable);
}
