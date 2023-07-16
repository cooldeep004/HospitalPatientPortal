package com.patient.patient.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionalHandler {
	
	@ExceptionHandler(patientNotFoundException.class)
	public ResponseEntity<String> patientNotFoundExceptionHandler(patientNotFoundException ex){
		String msg=ex.getMessage();
		return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){

		Map<String, String> reponse=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName=((FieldError)error).getField();
			String msg	=error.getDefaultMessage();
			reponse.put(fieldName, msg);
		});
		return new ResponseEntity<>(reponse,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(StaffNotFoundException.class)
	public ResponseEntity<String> staffNotFoundExceptionHandler(StaffNotFoundException ex){
		String msg=ex.getMessage();
		
		return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
	}
}
