package com.ttcn.vnuaexam.repository;

import com.ttcn.vnuaexam.dto.search.DepartmentSearchDto;
import com.ttcn.vnuaexam.dto.search.SearchDto;
import com.ttcn.vnuaexam.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByCode(String code);

    //HQL Query
    @Query(value = " FROM Department d WHERE d.code = :code and d.id <> :id ")
    List<Department> findByCodeAndNotId(String code, Long id);

    //JPQL
    @Query(value = "SELECT d FROM Department d " +
            "WHERE " +
            "((:#{#dto.keyword} IS NULL OR :#{#dto.keyword} = '') " +
            "OR (d.code LIKE CONCAT('%', :#{#dto.keyword}, '%') OR d.name LIKE CONCAT('%', :#{#dto.keyword}, '%'))) " +
            "AND (:#{#dto.code} IS NULL OR :#{#dto.code} = '' OR d.code = :#{#dto.code})",
            countQuery = "SELECT COUNT(d) FROM Department d " +
                    "WHERE " +
                    "((:#{#dto.keyword} IS NULL OR :#{#dto.keyword} = '') " +
                    "OR (d.code LIKE CONCAT('%', :#{#dto.keyword}, '%') OR d.name LIKE CONCAT('%', :#{#dto.keyword}, '%'))) " +
                    "AND (:#{#dto.code} IS NULL OR :#{#dto.code} = '' OR d.code = :#{#dto.code})")
    Page<Department> searchDepartment(@Param("dto") DepartmentSearchDto dto, Pageable pageable);
}
