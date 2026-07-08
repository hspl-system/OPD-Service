package com.healthify.opdservice.controller;

import com.healthify.opdservice.DTO.request.CreateConsultationRequest;
import com.healthify.opdservice.func.CreateConsultationFunc;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@RestController
@RequestMapping("/consultation")
public class ConsultationController {

    @Autowired
    private CreateConsultationFunc createConsultationFunc;
    private static Logger logger = LoggerFactory.getLogger(ConsultationController.class);

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<String> createConsultation(@Valid @RequestBody  CreateConsultationRequest request){

        if(request.isFirstConsultation()) {
            String result = createConsultationFunc.createConsultation(request);
            return ResponseEntity.ok()
                    .body(result);
        }

        return ResponseEntity.status(401).body("create a follow up");

    }

    @RequestMapping(value = "/test/get", method = RequestMethod.GET)
    public String hello(){
        return "Hello OPD service is working as expected";
    }


}
