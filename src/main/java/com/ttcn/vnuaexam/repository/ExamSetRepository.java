package com.ttcn.vnuaexam.repository;

import com.ttcn.vnuaexam.entity.ExamSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamSetRepository extends JpaRepository<ExamSet, Long> {

}
