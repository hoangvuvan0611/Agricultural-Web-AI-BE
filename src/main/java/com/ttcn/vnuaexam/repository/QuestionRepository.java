package com.ttcn.vnuaexam.repository;

import com.ttcn.vnuaexam.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByContent(String content);

    @Query(" FROM Question q WHERE q.content = :content AND q.id <> :id ")
    List<Question> findByContentAndNotId(String content, Long id);
}
