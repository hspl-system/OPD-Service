package com.healthify.opdservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class HelloWorldController {

    @Autowired
    UserDetailsManager userDetailsManager;

    @RequestMapping("/hello")
    public String helloWorld(){
        return "hello world!!";
    }

    @RequestMapping(value = "/createUser",method = RequestMethod.GET)
    public ResponseEntity<String> createUSer(@RequestParam String userName, @RequestParam String password){
        UserDetails userDetails = User.builder().username(userName).password(password).authorities(new ArrayList<>()).build();

        userDetailsManager.createUser(userDetails);

        return ResponseEntity.ok().body("Created User "+userName);
    }


}
