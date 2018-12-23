package com.bigeater.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class UserPo {
    private Long id;

    private String username;

    private String password;

    private String email;

    private Date lastPasswordResetDate;

    private List<String> roles;

    private Long ctime;

    private Long mtime;

    public Long getId() {
        return id;
    }
}