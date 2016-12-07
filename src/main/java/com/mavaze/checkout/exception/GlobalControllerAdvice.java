package com.mavaze.checkout.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public @ResponseBody ErrorInfo handleConflict(DataIntegrityViolationException ex, Locale locale) {
		String message = messageSource.getMessage("data.integrity.exception", null, locale);
		return new ErrorInfo(ErrorCode.DUPLICATE, message, null);
    }
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public @ResponseBody ErrorInfo processValidationError(ConstraintViolationException ex, Locale locale) {

		Set<ConstraintViolation<?>> results = ex.getConstraintViolations();
		List<String> details = new ArrayList<>(results.size());

		for (ConstraintViolation<?> result : results) {
			details.add(result.getMessage());
		}
		
		String message = messageSource.getMessage("constraint.violation.exception", null, locale);
		return new ErrorInfo(ErrorCode.CONSTRAINT_VIOLATION, message, details);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BadRequestException.class)
	public @ResponseBody ErrorInfo processBadRequestException(BadRequestException ex, Locale locale) {
		return new ErrorInfo(ex.getErrorCode(), buildBusinessExceptionMessage(ex, locale), null);
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(NotAuthorizedException.class)
	public @ResponseBody ErrorInfo processNotAuthorizedException(NotAuthorizedException ex, Locale locale) {
		return new ErrorInfo(ex.getErrorCode(), buildBusinessExceptionMessage(ex, locale), null);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public @ResponseBody ErrorInfo processNotFoundException(NotFoundException ex, Locale locale) {
		return new ErrorInfo(ex.getErrorCode(), buildBusinessExceptionMessage(ex, locale), null);
	}

	private String buildBusinessExceptionMessage(BusinessException ex, Locale locale) {
		return messageSource.getMessage(ex.getMessageProperty(), ex.getMessageArguments(), locale);
	}
	
}
