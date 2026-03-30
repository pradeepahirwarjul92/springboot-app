package com.heg.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResouceNotFound(ResourceNotFoundException ex){
		
		ErrorResponse error = new ErrorResponse(
				ex.getMessage(), 
				HttpStatus.NOT_FOUND.value(), 
				System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex){
		ErrorResponse error = new ErrorResponse(
				"Internal Server Error", 
				HttpStatus.INTERNAL_SERVER_ERROR.value(), 
				System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException ex){
		Map<String,String> errors = new HashMap<>();
		
		ex.getBindingResult().getFieldErrors().forEach(error ->
		errors.put(error.getField(), error.getDefaultMessage())
				);
		
		return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
		
	}

}
