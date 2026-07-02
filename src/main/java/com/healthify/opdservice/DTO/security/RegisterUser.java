package com.healthify.opdservice.DTO.security;

import com.healthify.opdservice.util.validators.annotations.PasswordValidator;

public class RegisterUser {
    private String userName;

    @PasswordValidator
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
