package com.ttcn.vnuaexam.repository;

import com.ttcn.vnuaexam.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByCode(String code);

    @Query(" FROM Answer a WHERE a.code = :code AND a.id <> :id ")
    List<Answer> findByCodeAndNotId(String code, Long id);

    List<Answer> findByQuestionId(Long questionId);
}
