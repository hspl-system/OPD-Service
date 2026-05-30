package com.healthify.opdservice.controller;

import com.healthify.opdservice.DTO.security.LoginDetails;
import com.healthify.opdservice.func.security.AuthenticateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller()
@RequestMapping("/security")
public class SecurityController {

    @Autowired
    AuthenticateUserService auth;

    @PostMapping("/process-login")
    @ResponseBody
    public String processLogin(LoginDetails login){

        String jwt = null;

        jwt = auth.autehticateUser(login);

        System.out.println("*** jwt:"+jwt);
        return jwt;
    }

    @GetMapping("/login")
    public String login(){  return "login"; }

}
