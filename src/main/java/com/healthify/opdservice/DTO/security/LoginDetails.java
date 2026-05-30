package com.healthify.opdservice.DTO.security;

import com.healthify.opdservice.util.validators.annotations.PasswordValidator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LoginDetails {
    @NotNull
    @NotBlank
    private final String userName;

    private final String password;

    public LoginDetails(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

}
