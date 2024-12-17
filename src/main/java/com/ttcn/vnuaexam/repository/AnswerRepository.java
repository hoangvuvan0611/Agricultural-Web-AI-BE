package com.ttcn.vnuaexam.repository;

import com.ttcn.vnuaexam.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuestionId(Long questionId);

    void deleteByQuestionId(Long questionId);

    @Query(" DELETE FROM Answer ans WHERE ans.questionId in : ids")
    void deleteByQuestionIds(List<Long> ids);
}
