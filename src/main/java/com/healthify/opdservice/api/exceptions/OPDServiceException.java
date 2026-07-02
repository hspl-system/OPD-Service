package com.healthify.opdservice.api.exceptions;

import com.healthify.opdservice.enums.OPDServiceExceptionErrorCodes;

public class OPDServiceException extends RuntimeException{

    private Object[] args;

    // accepts  0 or more parameters and error code while exception is thrown
    // these parameters can be used to populate error message placeholders when cought in exception handler
    public OPDServiceException(OPDServiceExceptionErrorCodes message, Object... args){
        super(message.name());
        this.args = args;
    }

    public OPDServiceException(OPDServiceExceptionErrorCodes message, Throwable cause){

        super(message.name(),cause);
    }

    //getter for args in exception handler to fetch parameters
    public Object[] getArgs() {
        return args;
    }

    //getter for error code in exception handler to fetch error code
    public String getErrorCode() {
        return this.getMessage();
    }
}
