package com.geography.distance.service.exceptions;

public class IllegalArgumentCustomException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public IllegalArgumentCustomException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalArgumentCustomException(String message) {
		super(message);
	}

}
