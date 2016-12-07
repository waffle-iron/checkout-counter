package com.mavaze.checkout.exception;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -1154861056499765877L;

    private final ErrorCode errorCode;
    
    private final String messageProperty;

    private final Object[] messageArguments;
    
    public BusinessException(ErrorCode errorCode, String messageProperty) {
        this(errorCode, messageProperty, null, null);
    }

    public BusinessException(ErrorCode errorCode, String messageProperty, Object[] messageArguments) {
        this(errorCode, messageProperty, messageArguments, null);
    }

    public BusinessException(ErrorCode errorCode, String messageProperty, Object[] messageArguments, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.messageProperty = messageProperty;
        this.messageArguments = messageArguments;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
    
    public String getMessageProperty() {
    	return messageProperty;
    }

    public Object[] getMessageArguments() {
        return messageArguments;
    }

}

