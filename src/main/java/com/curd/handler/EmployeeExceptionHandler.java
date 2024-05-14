
package com.curd.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.curd.exception.EmployeeNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;


@RestControllerAdvice
public class EmployeeExceptionHandler   {
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<String> handleException(EmployeeNotFoundException exp){
	 return	new ResponseEntity<String>(exp.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
}












