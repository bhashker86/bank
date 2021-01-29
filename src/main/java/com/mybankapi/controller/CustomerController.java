package com.mybankapi.controller;

import java.util.Optional;
import java.util.Random;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mybankapi.dto.CustomerDTO;
import com.mybankapi.entity.Account;
import com.mybankapi.entity.Customer;
import com.mybankapi.exception.CustomerExistException;
import com.mybankapi.service.CustomerService;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	public ModelMapper modelMapper;
	
	@PostMapping("/reg-customer")
	public ResponseEntity<Customer> addCustomer(@RequestBody CustomerDTO c){
		
		
		Customer customer= null;
	    Optional<Customer> custOpt=	customerService.getCustomerByAadharNumberOrPanNumber(c.getAadharNumber(),c.getPanNumber());		
				
		if(custOpt.isPresent()) {
			 throw new  CustomerExistException(c.getAadharNumber());
		}
		
		Long custNumber=new Long(generateCustomerNumber());
		
		Customer cust=new Customer();
		
		cust.setAadharNumber(c.getAadharNumber());
		cust.setEmail(c.getEmail());
		cust.setFirstName(c.getFirstName());
		cust.setLastName(c.getLastName());
		cust.setMobile(c.getMobile());
		cust.setPanNumber(c.getPanNumber());
		cust.setCustomerNumber(custNumber);
		Account act=new Account();
		
		act.setActBalance(null);
		act.setActNumber(custNumber);
		cust.setAct(act);
		
				 
		return new ResponseEntity<Customer>(customerService.createCustomer(cust),HttpStatus.CREATED);
	}
	
	
	private int generateCustomerNumber() {
		Random r = new Random();
		int lowerBound = 1000;
		int upperBound = 5000;
		int result = r.nextInt(upperBound-lowerBound) + lowerBound;
		return result;
	}
}
