package com.ttcn.vnuaexam.repository;

import com.ttcn.vnuaexam.entity.Question;
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
}
