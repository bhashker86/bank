package com.mybankapi.service;

import com.mybankapi.entity.Account;
import com.mybankapi.entity.Customer;

public interface AccountService {

	public Boolean deposite(Double amount,Customer c);
	public Boolean withDraw(Double amount,Customer c);
	
	public boolean impsTransfer(Account srcAct,Account destAct,Double amount);
	
}
