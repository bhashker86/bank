package com.mybankapi.service;

import java.util.Date;
import java.util.List;

import com.mybankapi.entity.Transaction;

public interface TransactionService {

	public List<Transaction> getTransactionForRangOfDate(Long ac,Date start,Date end);
	
}
