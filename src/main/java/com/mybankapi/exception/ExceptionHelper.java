package com.mybankapi.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHelper {

		
	@ExceptionHandler(value = {InsufficientBalanceException.class })
	  public ResponseEntity<Object> handleInsufficientBalanceException(InsufficientBalanceException ex) {
	        return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	
	    }
	
	 @ExceptionHandler(value = {InvalidAccountException.class })
	  public ResponseEntity<Object> handleInvalidAccountExceptionn(InvalidAccountException ex) {
	        return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	
	  }
	 
	 @ExceptionHandler(value = {InvalidAmountException.class })
	  public ResponseEntity<Object> handleInvalidAmountException(InvalidAmountException ex) {
	        return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	
	  }
	 @ExceptionHandler(value = {CustomerExistException.class })
	  public ResponseEntity<Object> handleCustomerExistException(CustomerExistException ex) {
	        return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	
	  }
	 @ExceptionHandler(value = {InvalidRequestException.class })
	  public ResponseEntity<Object> handleInvalidRequestException(InvalidRequestException ex) {
	        return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	
	  }
	 
	
}
