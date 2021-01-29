package com.mybankapi.exception;

public class InvalidAccountException extends RuntimeException {

	public  InvalidAccountException(Long id) {
		super("Account not found with  account id: "+id);
	}
}
