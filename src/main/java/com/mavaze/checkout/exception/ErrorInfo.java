package com.mavaze.checkout.exception;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ErrorInfo {
	
    public final ErrorCode code;
    
    public final String message;
    
    @JsonInclude(Include.NON_NULL)
    public final List<String> details;

    public ErrorInfo(ErrorCode code, String message, List<String> details) {
        this.code = code;
        this.message = message;
        this.details = details;   
    }
}