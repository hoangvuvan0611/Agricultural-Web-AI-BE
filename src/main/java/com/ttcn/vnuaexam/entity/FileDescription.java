package com.ttcn.vnuaexam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "file_description")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileDescription extends BaseEntity {
    @Column(name = "content_size")
    private Integer contentSize;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "name")
    private String name;

    @Column(name = "file_path")
    private String filePath;
}
