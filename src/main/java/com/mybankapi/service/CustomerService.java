package com.mybankapi.service;

import java.util.Optional;

import com.mybankapi.entity.Customer;

public interface CustomerService {
 
	public Customer createCustomer(Customer c);	
	public Optional<Customer> getCustomerByNumber(Long id);
	
	public Optional<Customer> getCustomerByAadharNumberOrPanNumber(String aadhar,String pan);
 
}
