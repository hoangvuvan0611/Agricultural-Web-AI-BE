package com.ttcn.vnuaexam.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "file_description")
public class FileDescription {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "modify_date")
    private Date modifyDate;

    @Column(name = "content_size")
    private int contentSize;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "name")
    private String name;

}
