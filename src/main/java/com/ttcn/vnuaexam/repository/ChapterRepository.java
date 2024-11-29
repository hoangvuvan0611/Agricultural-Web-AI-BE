package com.ttcn.vnuaexam.repository;

import com.ttcn.vnuaexam.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    List<Chapter> findByNameAndSubjectId(String name, Long subjectId);

    //HQL Query
    @Query(value = " FROM Chapter d WHERE d.subjectId = :subjectId and d.name = :name and d.id <> :id ")
    List<Chapter> findByNameAndNotId(String name, Long id, Long subjectId);
}
