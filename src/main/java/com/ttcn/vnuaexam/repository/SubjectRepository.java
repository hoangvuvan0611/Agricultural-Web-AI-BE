package com.ttcn.vnuaexam.repository;

import com.ttcn.vnuaexam.corollary.SubjectResultSetResponse;
import com.ttcn.vnuaexam.dto.response.SubjectResponseDto;
import com.ttcn.vnuaexam.dto.search.SubjectSearchDto;
import com.ttcn.vnuaexam.entity.Subject;
import org.springframework.beans.factory.annotation.Value;
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

    @Query(value = " FROM Subject s WHERE s.code = :code AND s.id <> :id ")
    List<Subject> findByCodeAndNotId(String code, Long id);


    List<Subject> findByName(String name);

    @Query(value = " FROM Subject s WHERE s.name = :name AND s.id <> :id ")
    List<Subject> findByNameAndNotId(String name, Long id);

    @Query(value = "FROM Subject sbj " +
            " LEFT JOIN UserSubject usbj " +
            " ON sbj.id = usbj.subjectId " +
            " WHERE (:#{#dto.userId} is null or usbj.userId = :#{#dto.userId}) " +
            " and (:#{#dto.keyword} is null or :#{#dto.keyword} = '' " +
            " or sbj.code LIKE CONCAT('%', :#{#dto.keyword}, '%') " +
            " or sbj.name LIKE CONCAT('%', :#{#dto.keyword}, '%') " +
            " or sbj.description LIKE CONCAT('%', :#{#dto.keyword}, '%'))")
    Page<Subject> search(@Param("dto") SubjectSearchDto dto, Pageable pageable);
}
