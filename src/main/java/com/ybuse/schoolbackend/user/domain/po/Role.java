package com.ybuse.schoolbackend.user.domain.po;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;

public class Role implements GrantedAuthority {
    @Id
    private int id;
    private String code;

    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id && Objects.equals(code, role.code) && Objects.equals(title, role.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, title);
    }

    @Override
    public String getAuthority() {
        return getCode();
    }
}
