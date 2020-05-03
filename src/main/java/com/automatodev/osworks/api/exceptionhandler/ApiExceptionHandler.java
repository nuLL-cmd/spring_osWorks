package com.automatodev.osworks.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		var fields = new ArrayList<EntityHandler.FieldHndler>();
		var entityHandler = new EntityHandler();

		for (ObjectError error: ex.getBindingResult().getAllErrors()) 
			fields.add(new EntityHandler.FieldHndler(error.getDefaultMessage(), ((FieldError) error).getField()));
		
		entityHandler.setStatus(status.value());
		entityHandler.setTitleString("Um ou mais campos são invalidos, tente novamente");
		entityHandler.setTime(LocalDateTime.now());
		entityHandler.setFieldHandlerLiset(fields);
		return super.handleExceptionInternal(ex,entityHandler, headers, status, request);

	
	}
}
