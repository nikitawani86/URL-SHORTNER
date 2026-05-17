package com.example.url.URL_SHORTNER.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(URLNotFoundException.class)
	public ResponseEntity<String> handlerUrlNotFound(URLNotFoundException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(ex.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleValdiation(MethodArgumentNotValidException ex){
		Map<String,String> errors  = new HashMap<>();
		
		ex.getBindingResult()
		.getFieldErrors()
		.forEach(error -> 
		errors.put(error.getField(), error.getDefaultMessage())
		);
		
		return ResponseEntity.badRequest().body(errors);
	}

}
