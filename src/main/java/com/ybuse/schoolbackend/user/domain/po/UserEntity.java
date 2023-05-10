package com.ybuse.schoolbackend.user.domain.po;

import org.springframework.data.annotation.Id;

import java.util.Objects;

public class UserEntity {

    @Id
    private String username;

    private String password;

    private String title;

    private byte enable;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte getEnable() {
        return enable;
    }

    public void setEnable(byte enable) {
        this.enable = enable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return enable == that.enable && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, title, enable);
    }
}
