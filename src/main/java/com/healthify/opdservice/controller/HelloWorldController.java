package com.healthify.opdservice.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;

@Controller
public class HelloWorldController {

    public static final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

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

    @RequestMapping(value = "/createUser",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> createUSer(@RequestParam String userName ,@Valid @RequestParam @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{8,}$") String password){

        UserDetails userDetails = User.builder().username(userName).password(password).authorities(new ArrayList<>()).build();

        userDetailsManager.createUser(userDetails);

        return ResponseEntity.ok().body("Created User "+userName);
    }



    //endpoint just to test cookies
    @RequestMapping(value = "/getCookies", method = RequestMethod.GET)
    public ResponseEntity<String> getCookies() {
        String userName = null;

        //get httpSvltRequest from thread
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        //get cookiees from request's header
        if (request.getCookies() != null) {
            for (Cookie c : request.getCookies()) {
                if (c.getName().equals("UserName")) {
                    userName = c.getValue();
                } else {
                    userName = ",UserName cookie not found";
                }
            }
        } else {
            userName = ",Please enable cookies";
        }

        //get sessionAttribute val
        HttpSession sess = request.getSession();
        String val = (String) sess.getAttribute("key1");


        return ResponseEntity.ok("hello " + userName+" also has: "+val);
    }

    @RequestMapping(value = "/home")
    public ResponseEntity<String> home(){
        //get ServletRequest to read custom header
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest requestSvlt = requestAttributes.getRequest();
        logger.info("*******custom Header************ {}", requestSvlt.getHeader("custom"));

        //Create a cookiee and send it in responseEntity
        ResponseCookie cookie = ResponseCookie.from("UserName", "defaultUser")
                .httpOnly(true)
                .path("/")
                .maxAge(24 * 60 * 60) // 1 day
                .build();

        //Add a session atribute
        HttpSession session = requestSvlt.getSession();
        session.setAttribute("key1", "sessionAttrVal");

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body("welcome");   }


}
