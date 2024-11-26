package com.ttcn.vnuaexam.repository;

import com.ttcn.vnuaexam.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByCode(String code);

    @Query(" FROM Question q WHERE q.code = :code AND q.id <> :id ")
    List<Question> findByCodeAndNotId(String code, Long id);
}
