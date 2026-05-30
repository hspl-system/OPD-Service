package com.healthify.opdservice.util.utils;

import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;

public class SecurityUtils {

    public static Key buildKeyFromSecret(String secret){

        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
}
