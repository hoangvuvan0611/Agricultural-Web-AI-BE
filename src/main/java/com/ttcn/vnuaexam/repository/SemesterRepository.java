package com.ttcn.vnuaexam.repository;

import com.ttcn.vnuaexam.entity.Answer;
import com.ttcn.vnuaexam.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Long> {

}
