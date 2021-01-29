package com.mybankapi.exception;

public class InvalidAmountException extends RuntimeException {

	public  InvalidAmountException(Double  amount) {
		super("Invalid Amount: "+amount);
	}
}
