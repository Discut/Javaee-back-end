package com.ybuse.schoolbackend.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TokenUtilTest {

    @Test
    void getExpirationInterval() {
        TokenUtil.getExpirationInterval("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2ODM3MDM0NjgsImlzcyI6InN5c3RlbUFkbWluIiwiZXhwIjoxNjgzNzg5ODY4LCJlbmFibGUiOnRydWUsInJvbGVzIjpbeyJpZCI6MSwiY29kZSI6IkFDVElWSVRJX1VTRVIiLCJ0aXRsZSI6Iua1geeoi-aOp-WItuS9v-eUqOiAhSJ9LHsiaWQiOjQsImNvZGUiOiJTVFVERU5UIiwidGl0bGUiOiLlrabnlJ8ifV0sInVzZXJuYW1lIjoiMSJ9.2NBU0b1FQUSYJxAAEhD1thpT3boLqx6d9PgR2E9IoWA");
    }
}