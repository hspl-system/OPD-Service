package com.healthify.opdservice.func.security;

import com.healthify.opdservice.DTO.security.LoginDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;
import  com.healthify.opdservice.util.utils.SecurityUtils;


@Service
public class AuthenticateUserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${dev.jwt.secret}")
    private  String authSecret;


    public AuthenticateUserService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    public String autehticateUser(LoginDetails login){

        String jwt;

        //autehnticate the user
        Authentication authentication = authenticationWithManager(login);

        // if user is authenticated create a JWT else : throw a 401 and USerDoesntExistExecption

        if(authentication.isAuthenticated()){
            jwt = generateJwt(authentication);
        } else {
            throw new AuthenticationCredentialsNotFoundException("Unable to Authenticate user");
        }



        return jwt;

    }


    private Authentication authenticationWithManager(LoginDetails loginDetails) {
        String user = loginDetails.getUserName();
        String password = loginDetails.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user,password);

        Authentication authentication = authenticationManager.authenticate(token);

        System.out.println("**************"+authentication.isAuthenticated() +"******"+authentication.getName()+"*******"+authentication.getAuthorities().toString());

        return authentication;
    }

    private String generateJwt(Authentication authentication){

        String  jwt ;
        Instant now = Instant.now();
        Key key = SecurityUtils.buildKeyFromSecret(authSecret);

        jwt = Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(authentication.getName())
                .setIssuer("healthify")
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(3600))) // 1 hour
                .claim("roles", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .signWith(key)
                .compact();

        return jwt;

    }


}
