package com.ttcn.vnuaexam.repository;

import com.ttcn.vnuaexam.dto.search.SearchDto;
import com.ttcn.vnuaexam.entity.Semester;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Long> {
    @Query("FROM Semester semester WHERE " +
            "(:#{#dto.keyword} IS NULL OR :#{#dto.keyword} = '' OR " +
            "semester.code LIKE %:#{#dto.keyword}% OR " +
            "semester.title LIKE %:#{#dto.keyword}% OR " +
            "CAST(semester.status as string) = :#{#dto.keyword})")
    Page<Semester> search(@Param("dto") SearchDto dto, Pageable pageable);
}
