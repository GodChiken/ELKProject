package com.kbh.elk.app.config.exception.handler;

import com.kbh.elk.app.config.exception.common.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@AllArgsConstructor
@RestControllerAdvice
public class RestExceptionHandler {

	private static ErrorMessageMap messageMap;

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity businessException(BusinessException e) {
		return new ResponseEntity(createErrorEntity(e.getErrorCode(), HttpStatus.UNPROCESSABLE_ENTITY), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(FieldValidationException.class)
	public ResponseEntity fieldValidationException(BusinessException e) {
		return new ResponseEntity(createErrorEntity(e.getErrorCode(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoResourceException.class)
	public ResponseEntity noResourceException(BusinessException e) {
		return new ResponseEntity(createErrorEntity(e.getErrorCode(), HttpStatus.FORBIDDEN), HttpStatus.FORBIDDEN);
	}

	public ErrorEntity createErrorEntity(int errCode, HttpStatus httpStatus) {
		return ErrorEntity.builder()
				.code(errCode)
				.message(messageMap.getMessage(errCode))
				.status(String.valueOf(httpStatus)).build();
	}
}
