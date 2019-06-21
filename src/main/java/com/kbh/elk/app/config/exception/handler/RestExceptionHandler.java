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

	private ErrorMessageMap messageMap;

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity businessException(BusinessException e) {
		return mappingErrorToResponseEntity(e,HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(FieldValidationException.class)
	public ResponseEntity fieldValidationException(FieldValidationException e) {
		return mappingErrorToResponseEntity(e,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoResourceException.class)
	public ResponseEntity noResourceException(NoResourceException e) {
		return mappingErrorToResponseEntity(e,HttpStatus.FORBIDDEN);
	}

	private ResponseEntity mappingErrorToResponseEntity(BaseException e, HttpStatus status) {
		return new ResponseEntity(createErrorEntity(e.getErrorCode(), status), status);
	}

	private ErrorEntity createErrorEntity(int errCode, HttpStatus httpStatus) {
		return ErrorEntity.builder()
				.code(errCode)
				.message(messageMap.getMessage(errCode))
				.status(String.valueOf(httpStatus.value())).build();
	}
}
