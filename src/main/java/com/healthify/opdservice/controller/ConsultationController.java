package com.healthify.opdservice.controller;

import com.healthify.opdservice.DTO.request.CreateConsultationRequest;
import com.healthify.opdservice.func.CreateConsultationFunc;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest requestSvlt = requestAttributes.getRequest();

        logger.info("*******custom Header************ {}", requestSvlt.getHeader("custom"));
        logger.info("in ConsultationController.createConsultation");
        String result = createConsultationFunc.createConsultation(request);
        return  ResponseEntity.ok(result);

    }
}
