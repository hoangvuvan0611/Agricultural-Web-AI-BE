package com.ttcn.vnuaexam.repository;

import com.ttcn.vnuaexam.constant.enums.Role;
import com.ttcn.vnuaexam.dto.search.SearchDto;
import com.ttcn.vnuaexam.dto.search.UserSearchDto;
import com.ttcn.vnuaexam.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    final Integer roleStudent = Role.STUDENT.getNumRole();

    Optional<User> findByCode(String code);

    @Query(" FROM User q WHERE q.code = :code AND q.id <> :id ")
    Optional<User> findByCodeAndIdNot(String code, Long id);

    Optional<User> findByUsername(String username);

    @Query(" FROM User q WHERE q.username = :username AND q.id <> :id ")
    Optional<User> findByUsernameAndIdNot(String username, Long id);

    @Query(value = " FROM User user " +
            " WHERE (:#{#dto.keyword} IS NULL OR :#{#dto.keyword} = '' " +
            "        OR user.code LIKE CONCAT('%', :#{#dto.keyword}, '%') " +
            "        OR user.fullName LIKE CONCAT('%', :#{#dto.keyword}, '%')" +
            "        OR user.classCode LIKE CONCAT('%', :#{#dto.keyword}, '%'))" +
            " AND :#{#dto.userRole} is null OR user.role = :#{#dto.userRole} ")
    Page<User> searchStudent(@Param("dto") UserSearchDto dto, Pageable pageable);

    @Query(value = " FROM User user " +
            " WHERE (:#{#dto.keyword} IS NULL OR :#{#dto.keyword} = '' " +
            "        OR user.code LIKE CONCAT('%', :#{#dto.keyword}, '%') " +
            "        OR user.fullName LIKE CONCAT('%', :#{#dto.keyword}, '%'))" +
            " AND :#{#dto.userRole} is null OR user.role = :#{#dto.userRole} ")
    List<User> searchUserList(@Param("dto") UserSearchDto dto);
}
