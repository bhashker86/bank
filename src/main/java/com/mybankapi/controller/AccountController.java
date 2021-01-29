package com.mybankapi.controller;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mybankapi.entity.Account;
import com.mybankapi.entity.Customer;
import com.mybankapi.exception.InsufficientBalanceException;
import com.mybankapi.exception.InvalidAccountException;
import com.mybankapi.exception.InvalidAmountException;
import com.mybankapi.exception.InvalidRequestException;
import com.mybankapi.service.AccountService;
import com.mybankapi.service.CustomerService;

@RestController
@RequestMapping("api/v1/account")
public class AccountController {
    
	@Autowired 
	private AccountService accountServ;
	
	@Autowired 
	private CustomerService customerService;

	private static final Log LOG=LogFactory.getLog(AccountController.class);
	
	@PostMapping("/deposite")
	public ResponseEntity<Boolean> depositeAmount(@RequestParam Long sourceAcn  ,@RequestParam Double amount) {
		
		Optional<Customer> custOpt=	customerService.getCustomerByNumber(sourceAcn);	
            
        if(custOpt.isEmpty()) {
        	throw new InvalidAccountException(sourceAcn);
        }
        Customer c=custOpt.get();
        
        if(amount<=0) {
        	throw new InvalidAmountException(amount);
        }
		return new ResponseEntity<Boolean>(accountServ.deposite(amount, c),HttpStatus.OK);
	}
	
	@PostMapping("/withdrawal")
	public ResponseEntity<Boolean> withDrawAmount(@RequestParam Long sourceAcn , @RequestParam Double amount) {
		
		Optional<Customer> custOpt=	customerService.getCustomerByNumber(sourceAcn);	
		
		if(custOpt.isEmpty()) {
	        	throw new InvalidAccountException(sourceAcn);
        }
		 Customer c=custOpt.get();
        if(amount<=0) {
        	throw new InvalidAmountException(amount);
        }
       if( c.getAct().getActBalance()<amount) {
    	    throw new InsufficientBalanceException(sourceAcn);
       }
		return new ResponseEntity<Boolean>(accountServ.withDraw(amount, c)    ,HttpStatus.OK);
	}
	
	
	@PostMapping("/transfer")
	public ResponseEntity<Boolean> impsTransfer(
			@RequestParam("sourceCoustomerId") Long  sourceCoustomerId, 
			@RequestParam("destCounstomerId")Long destCounstomerId, 
			@RequestParam("amount")Double amount)
	{
		Account sourceAct=null;
		Account desctAct=null;
		LOG.info("Method Start: "+"impsTransfer");
		LOG.info("User input source customer id:"+sourceCoustomerId);
		LOG.info("User input destination customer id:"+destCounstomerId);
		
		if(amount<=0) {
        	throw new InvalidAmountException(amount);
        }
		Optional<Customer> custOptS=	customerService.getCustomerByNumber(sourceCoustomerId);	
		Optional<Customer> custOptD=	customerService.getCustomerByNumber(destCounstomerId);	
		
		if(custOptS.isEmpty() && custOptD.isEmpty()) {
			throw new InvalidRequestException();
		}
		
		sourceAct=custOptS.get().getAct();
		desctAct=custOptD.get().getAct();
		
		if(sourceAct==desctAct) {
			throw new InvalidRequestException();
		}
		if(sourceAct.getActBalance()<amount) {
			throw new InsufficientBalanceException(sourceAct.getActNumber());
		}
		
		
		LOG.info("Source Account:"+sourceAct);
		LOG.info("Destination Account:"+desctAct);
		
		if(accountServ.impsTransfer(sourceAct, desctAct, amount)) {
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		}
		 return new ResponseEntity<Boolean>(false,HttpStatus.OK);
	}
	
	
}
