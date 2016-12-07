package com.mavaze.checkout.exception;

public class NotAuthorizedException extends BusinessException {

    private static final long serialVersionUID = 6700420623133685384L;

    private final static ErrorCode errorCode = ErrorCode.NOT_AUTHORIZED;

    public NotAuthorizedException(String messageProperty) {
        super(errorCode, messageProperty);
    }
    
    public NotAuthorizedException(String messageProperty, Object[] messageArguments) {
        super(errorCode, messageProperty, messageArguments);
    }
    
    public NotAuthorizedException(String messageProperty, Object[] messageArguments, Throwable cause) {
        super(errorCode, messageProperty, messageArguments, cause);
    }
}
