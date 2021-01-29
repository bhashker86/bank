package com.mybankapi.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybankapi.entity.Transaction;
import com.mybankapi.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private TransactionRepository transactionRepo;
	
	public List<Transaction> getTransactionForRangOfDate(Long ac,Date start,Date end){
		//return transactionRepo.findByActNumber(ac);
		return transactionRepo.getTransactionForRangeOfDate(ac,start,end);
	}
}
