package com.mybankapi.exception;

public class InsufficientBalanceException extends RuntimeException {

	public  InsufficientBalanceException(Long id) {
		super("Insufficent Balance in account number: "+id);
	}
	
}
