package com.automatodev.osworks.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		var entityHandler = new EntityHandler();
		
		entityHandler.setStatus(status.value());
		entityHandler.setTitleString("Um ou mais campos são invalidos, tente novamente");
		entityHandler.setTime(LocalDateTime.now());
		
		return super.handleExceptionInternal(ex,entityHandler, headers, status, request);
	}
}
