package com.ttcn.vnuaexam.repository;

import com.ttcn.vnuaexam.entity.FileDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FileDescriptionRepository extends JpaRepository<FileDescription, UUID> {
}
