package com.leonlyon.products.exceptions;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionControllerHandler {
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<Object> handleProductNotFound(ProductNotFoundException ex,WebRequest request){
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp",LocalDateTime.now());
		body.put("message",ex.getMessage());
		return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ProductNoContentException.class)
	public ResponseEntity<Object> handleNoProductFound(ProductNoContentException ex,WebRequest request){
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp",LocalDateTime.now());
		body.put("message",ex.getMessage());
		return new ResponseEntity<>(body,HttpStatus.NO_CONTENT);
	}

}
