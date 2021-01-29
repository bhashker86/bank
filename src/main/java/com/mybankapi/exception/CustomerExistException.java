package com.mybankapi.exception;

public class CustomerExistException extends RuntimeException {

	public  CustomerExistException(String id) {
		super("Customer already available with  cusotme aadhar  number: "+id);
	}
}
