package com.ttcn.vnuaexam.repository;

import com.ttcn.vnuaexam.dto.response.ChapterResponseDto;
import com.ttcn.vnuaexam.dto.search.ChapterSearchDto;
import com.ttcn.vnuaexam.entity.Chapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    List<Chapter> findByNameAndSubjectId(String name, Long subjectId);

    //HQL Query
    @Query(value = " FROM Chapter d WHERE d.subjectId = :subjectId and d.name = :name and d.id <> :id ")
    List<Chapter> findByNameAndNotId(String name, Long id, Long subjectId);

    void deleteBySubjectId(Long subjectId);

    @Query("FROM Chapter chp WHERE (:#{#dto.subjectId} is null OR chp.subjectId = :#{#dto.subjectId}) " +
            "AND (:#{#dto.keyword} IS NULL OR :#{#dto.keyword} = '' " +
            "OR chp.name LIKE CONCAT('%', :#{#dto.keyword}, '%') " +
            "OR chp.description LIKE CONCAT('%', :#{#dto.keyword}, '%'))")
    Page<Chapter> search(@Param("dto") ChapterSearchDto dto, Pageable pageable);
}
