package com.ttcn.vnuaexam.entity;

import com.ttcn.vnuaexam.constant.MessageCodes;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "tbl_user")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @Column(name = "username")
    @Length(max = 18, message = MessageCodes.NOT_TOO_LONG)
    @Pattern(regexp = "\\S+", message = MessageCodes.NOT_WHITESPACE)
    private String username;

    @Column(name = "password")
    @Pattern(regexp = "\\S+", message = MessageCodes.NOT_WHITESPACE)
    private String password;

    @Column(name = "code")
    @Pattern(regexp = "\\S+", message = MessageCodes.NOT_WHITESPACE)
    @Length(max = 12, message = MessageCodes.NOT_TOO_LONG)
    private String code;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "role")
    private Integer role;

    @Column(name = "is_active")
    private Boolean isActive;
}
