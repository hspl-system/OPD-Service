package com.healthify.opdservice.controllerAdvice;

import com.healthify.opdservice.api.apiErrors.ApiErrorResponse;
import com.healthify.opdservice.api.exceptions.OPDServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.*;
import java.util.*;

import java.util.Locale;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {
    //autowiring Message Source to get messages from messages.properties
    @Autowired
    MessageSource messageSource;

    //custom Exception
    @ExceptionHandler(OPDServiceException.class)
    public ResponseEntity<ApiErrorResponse> handleOPDException(OPDServiceException ex){
        ApiErrorResponse apiErrorResponse = createApiErrorResponse(ex);

        return ResponseEntity.status(apiErrorResponse.getStatusCode()).body(apiErrorResponse);
    }

    //validation Exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ApiErrorResponse>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<ApiErrorResponse> responses = createMethodArgumentNotValidExceptionResponse(ex);

        return ResponseEntity.status(400).body(responses);
    }


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiErrorResponse> handleBadCredentialsException(BadCredentialsException ex){

        ApiErrorResponse response = new ApiErrorResponse();
        response.setStatusCode(401);
        response.setErrorMessage(ex.getMessage());
        return ResponseEntity.status(response.getStatusCode()).body(response);

    }

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleAuthenticationCreadentialException(AuthenticationCredentialsNotFoundException ex){

        ApiErrorResponse response = new ApiErrorResponse();
        response.setStatusCode(401);
        response.setErrorMessage(ex.getMessage());
        return ResponseEntity.status(response.getStatusCode()).body(response);

    }

    private ApiErrorResponse createApiErrorResponse(OPDServiceException ex){
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        //populate fields
        String errorCode = ex.getErrorCode();
        apiErrorResponse.setErrorCode(errorCode);
        apiErrorResponse.setErrorMessage(messageSource.getMessage(errorCode, ex.getArgs(), null));
        apiErrorResponse.setStatusCode(500);
        return apiErrorResponse;
    }

    private List<ApiErrorResponse> createMethodArgumentNotValidExceptionResponse(MethodArgumentNotValidException ex){
        List<ApiErrorResponse> errorResponses = new ArrayList<>();
        for (ObjectError e : ex.getAllErrors()) {

            ApiErrorResponse res = new ApiErrorResponse();
            res.setStatusCode(ex.getStatusCode().value());
            res.setErrorMessage(e.toString());
            System.out.println("***************"+e.toString());
            errorResponses.add(res);
        }
        return errorResponses;
    }
}

