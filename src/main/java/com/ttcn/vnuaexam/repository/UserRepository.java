package com.ttcn.vnuaexam.repository;

import com.ttcn.vnuaexam.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByCode(String code);

    @Query(" FROM User q WHERE q.code = :code AND q.id <> :id ")
    Optional<User> findByCodeAndIdNot(String code, Long id);

    Optional<User> findByUsername(String username);

    @Query(" FROM User q WHERE q.username = :username AND q.id <> :id ")
    Optional<User> findByUsernameAndIdNot(String username, Long id);
}
