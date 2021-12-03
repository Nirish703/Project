package com.example.demo.advice;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.custom.exception.EmployeeNotFoundException;
import com.example.demo.custom.exception.InputEmptyException;

@ControllerAdvice
public class EmployeeExceptionController extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = EmployeeNotFoundException.class)
	public ResponseEntity<Object> employeeNotFound(EmployeeNotFoundException employeeNotFoundException){
		return new ResponseEntity<>("Employee not found",HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = InputEmptyException.class)
	public ResponseEntity<Object> inputEmptyException(InputEmptyException inputEmptyException){
		return new ResponseEntity<>("Input is Empty "+inputEmptyException.getErrorMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Object> noSuchElementException(NoSuchElementException elementException){
		return new ResponseEntity<>("No value is present in DB, please change your request",HttpStatus.NOT_FOUND);
	}
	
}
