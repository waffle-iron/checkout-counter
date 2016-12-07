package com.mavaze.checkout.exception;

public class NotFoundException extends BusinessException {

    private static final long serialVersionUID = -8568119418583853414L;

    private final static ErrorCode errorCode = ErrorCode.NOT_FOUND;

    public NotFoundException(String messageProperty) {
        super(errorCode, messageProperty);
    }
    
    public NotFoundException(String messageProperty, Object[] messageArguments) {
        super(errorCode, messageProperty, messageArguments);
    }
    
    public NotFoundException(String messageProperty, Object[] messageArguments, Throwable cause) {
        super(errorCode, messageProperty, messageArguments, cause);
    }

}
