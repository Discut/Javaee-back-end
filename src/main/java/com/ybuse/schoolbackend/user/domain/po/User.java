package com.ybuse.schoolbackend.user.domain.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Deprecated
public class User {
    private String account;
    private String password;
    private String title;
}
