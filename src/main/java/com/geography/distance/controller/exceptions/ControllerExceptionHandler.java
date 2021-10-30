package com.geography.distance.controller.exceptions;

import java.time.LocalDate;

import javax.servlet.ServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.geography.distance.service.exceptions.IllegalArgumentCustomException;
import com.geography.distance.service.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<DefaultError> objectNotFoundException(ObjectNotFoundException e, ServletRequest request) {
		DefaultError error = new DefaultError(LocalDate.now(), HttpStatus.NOT_FOUND.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(IllegalArgumentCustomException.class)
	public ResponseEntity<DefaultError> startHourNoLessThanEndHourException(IllegalArgumentCustomException e, ServletRequest request){
		DefaultError error = new DefaultError(LocalDate.now(), HttpStatus.BAD_REQUEST.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

}
