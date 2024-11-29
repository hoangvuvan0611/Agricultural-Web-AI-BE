package com.ttcn.vnuaexam.repository;

import com.ttcn.vnuaexam.corollary.SubjectResultSetResponse;
import com.ttcn.vnuaexam.dto.search.SubjectSearchDto;
import com.ttcn.vnuaexam.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findByCode(String code);

    List<Subject> findByName(String name);

    @Query(value = " FROM Subject s WHERE s.name = :name AND s.id <> :id ")
    List<Subject> findByNameAndNotId(String name, Long id);

    @Query(value = "SELECT s.id AS id, " +
            " s.code AS code, " +
            " s.name AS name," +
            " FROM tbl_subject s " +
            " WHERE (:#{#dto.keyword} IS NULL OR :#{#dto.keyword} = '' OR " +
            " s.code LIKE CONCAT('%', :#{#dto.keyword}, '%') OR " +
            " s.name LIKE CONCAT('%', :#{#dto.keyword}, '%')) AND " +
            " (:#{#dto.departmentId} IS NULL OR " +
            " s.department_id = :#{#dto.departmentId})",
            countQuery = "SELECT COUNT(*) FROM tbl_subject s " +
                    " LEFT JOIN tbl_department d ON s.department_id = d.id " +
                    " WHERE (:#{#dto.keyword} IS NULL OR :#{#dto.keyword} = '' OR " +
                    " s.code LIKE CONCAT('%', :#{#dto.keyword}, '%') OR " +
                    " s.name LIKE CONCAT('%', :#{#dto.keyword}, '%')) AND " +
                    " (:#{#dto.departmentId} IS NULL OR " +
                    " s.department_id = :#{#dto.departmentId})",
            nativeQuery = true)
    Page<SubjectResultSetResponse> search(@Param("dto") SubjectSearchDto dto, Pageable pageable);

}
