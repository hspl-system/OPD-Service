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

        String result = createConsultationFunc.createConsultation(request);

        return ResponseEntity.ok()
                .body(result);

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
