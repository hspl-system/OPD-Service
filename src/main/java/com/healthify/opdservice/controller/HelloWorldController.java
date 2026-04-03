package com.healthify.opdservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class HelloWorldController {

    @Autowired
    UserDetailsManager userDetailsManager;

    @RequestMapping("/hello")
    @ResponseBody
    public String helloWorld(){
        return "hello world!!";
    }

    @RequestMapping("/register")
    public String register(){
        return "registration";
    }

    @RequestMapping(value = "/createUser",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> createUSer(@RequestParam String userName, @RequestParam String password){
        UserDetails userDetails = User.builder().username(userName).password(password).authorities(new ArrayList<>()).build();

        userDetailsManager.createUser(userDetails);

        return ResponseEntity.ok().body("Created User "+userName);
    }



}
