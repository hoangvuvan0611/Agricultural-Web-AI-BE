package com.ttcn.vnuaexam.repository;

import com.ttcn.vnuaexam.dto.response.QuestionResponseDto;
import com.ttcn.vnuaexam.dto.search.ChapterSearchDto;
import com.ttcn.vnuaexam.dto.search.QuestionSearchDto;
import com.ttcn.vnuaexam.entity.Chapter;
import com.ttcn.vnuaexam.entity.Question;
import com.ttcn.vnuaexam.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByContent(String content);

    @Query(" FROM Question q WHERE q.content = :content AND q.id <> :id ")
    List<Question> findByContentAndNotId(String content, Long id);

    @Query(" FROM Question q WHERE q.id in :ids")
    List<Question> findByListIds(@Param("ids") List<Long> ids);

    void deleteBySubjectId(Long subjectId);

    void deleteByChapterId(Long chapterId);

    @Query(" DELETE FROM Question que WHERE que.id in : ids ")
    void deleteByQuestionIds(List<Long> ids);

    List<Question> findBySubjectId(Long subjectId);

    List<Question> findByChapterId(Long chapterId);

    @Query(value = " FROM Question ques " +
            " WHERE (:#{#dto.subjectId} is null OR ques.subjectId = :#{#dto.subjectId}) " +
            " AND (:#{#dto.chapterId} is null OR ques.chapterId = :#{#dto.chapterId}) " +
            " AND (:#{#dto.keyword} IS NULL OR :#{#dto.keyword} = ''" +
            " OR ques.content LIKE CONCAT('%', :#{#dto.keyword}, '%'))")
    Page<Question> search(@Param("dto") QuestionSearchDto dto, Pageable pageable);
}
