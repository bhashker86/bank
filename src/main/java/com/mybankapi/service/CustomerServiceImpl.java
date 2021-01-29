package com.mybankapi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mybankapi.entity.Customer;
import com.mybankapi.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository CustomerRepo;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public Customer createCustomer(Customer c) {
		
		Date date1=null;
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		try {
			 date1=sdf.parse(sdf.format(new Date()));
		}
		catch(ParseException e) {
			e.getLocalizedMessage();
			e.printStackTrace();
		}
		
		c.setCreateDateTime(date1);
		c.setUpdateDateTime(date1);
		c.setStatus("Avtive");
		
		c.getAct().setUpdateDateTime(date1);
		c.getAct().setCreateDateTime(date1);
		c.getAct().setActStatus("Active");
		
		
		return CustomerRepo.save(c);
	}
	
	public Optional<Customer> getCustomerByNumber(Long id) {
		return CustomerRepo.findByCustomerNumber(id);
	}
	
	public Optional<Customer> getCustomerByAadharNumberOrPanNumber(String aadhar,String pan){
		return CustomerRepo.getCustomerByAadharNumberOrPanNumber(aadhar, pan);
	}
}
