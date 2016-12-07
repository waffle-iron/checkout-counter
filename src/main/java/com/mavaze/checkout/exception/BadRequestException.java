package com.mavaze.checkout.exception;

public class BadRequestException extends BusinessException {

    private static final long serialVersionUID = -5870002374054125697L;
    
    private final static ErrorCode errorCode = ErrorCode.BAD_REQUEST;

    public BadRequestException(String messageProperty) {
        super(errorCode, messageProperty);
    }
    
    public BadRequestException(String messageProperty, Object[] messageArguments) {
        super(errorCode, messageProperty, messageArguments);
    }
    
    public BadRequestException(String messageProperty, Object[] messageArguments, Throwable cause) {
        super(errorCode, messageProperty, messageArguments, cause);
    }

}
