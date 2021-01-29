package com.mybankapi.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mybankapi.entity.Transaction;
import com.mybankapi.service.TransactionService;

@RestController
@RequestMapping("api/v1/transaction")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat month_date = new SimpleDateFormat("MMM-dd-yyyy");
	
	private static final Log LOG=LogFactory.getLog(TransactionController.class);
	
	@GetMapping("/getTransction")
	public ResponseEntity<List<Transaction>> getTransByAccount(@RequestParam Long actNo,
			@RequestParam String startDate,
			@RequestParam String endDate){
		
		Date date1=null;
		Date date2=null;
		LOG.info("User  input src date:"+startDate);
		LOG.info("User  input end date:"+endDate);
		
		try {
			  Date d1=month_date.parse(startDate);
			  Date d2=month_date.parse(endDate);
			  
			  LOG.info("After parse user input1:" +d1);
			  LOG.info("After parse user input2:" +d2);
			  
				 String da1= sdf.format(d1);
				 String da2=sdf.format(d2);
				 
				 LOG.info("After parse user input1 in String form:" +da1);
				 LOG.info("After parse user input2 in String form :" +da2);
			  
				 date1=sdf.parse(da1);
			     date2=sdf.parse(da2);
		     
		     LOG.info("User  input src date after format:"+date1);
			 LOG.info("User  input end date after format::"+date2);
		}
		catch(ParseException e) {
			e.getLocalizedMessage();
			e.printStackTrace();
		}
	
		
		
		return new ResponseEntity<List<Transaction>>(
				transactionService.getTransactionForRangOfDate(actNo,date1,date2),
				HttpStatus.OK);
	}

}
