package com.ttcn.vnuaexam.entity;

import com.ttcn.vnuaexam.constant.MessageCodes;
import com.ttcn.vnuaexam.constant.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "tbl_user")
@Getter
@Setter
public class User extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    @Length(max = 18,message = MessageCodes.NOT_TOO_LONG)
    @Pattern(regexp = "\\S+", message = MessageCodes.NOT_WHITESPACE)
    private String username;

    @Column(name = "password")
    @Pattern(regexp = "\\S+", message = MessageCodes.NOT_WHITESPACE)
    private String password;

    @Column(name = "code")
    @Pattern(regexp = "\\S+", message = MessageCodes.NOT_WHITESPACE)
    @Length(max = 12,message = MessageCodes.NOT_TOO_LONG)
    private String code;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "role")
    private Role role;

    @Column(name = "is_active")
    private Boolean isActive;
}
