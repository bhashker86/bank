package com.mybankapi.exception;

public class InvalidRequestException extends RuntimeException {

	public  InvalidRequestException() {
		super("Invalid Request");
	}
}
