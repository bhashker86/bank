package com.mybankapi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybankapi.entity.Account;
import com.mybankapi.entity.Customer;
import com.mybankapi.entity.Transaction;
import com.mybankapi.exception.InsufficientBalanceException;
import com.mybankapi.exception.InvalidRequestException;
import com.mybankapi.repository.AccountRepository;
import com.mybankapi.repository.TransactionRepository;


@Service
@Transactional
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private TransactionRepository tr;
	@Autowired
	private AccountRepository ar; 
	
	private static final Log LOG=LogFactory.getLog(AccountServiceImpl.class);
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat simpleFormat=new SimpleDateFormat("dd-MM-yyyy");
	
  public Boolean deposite(Double amount,Customer c) {
		Account act=c.getAct();
		 Optional<Account> optAcct=ar.findById(c.getAct().getId());
		 Account getSaveAccount=null;
		 if(!optAcct.isEmpty()) {
			 getSaveAccount=optAcct.get();
			 
		 }
		 Double currBalance=getSaveAccount.getActBalance();
		 if(currBalance==null) {
			 currBalance=new Double(0);
		 }
		 act.setActBalance(currBalance+amount);		 
			
			SimpleDateFormat sdtf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			Date acLastUpdate=null;
			Date transacDate=null;
			
			sdtf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
			simpleFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
			try {
				  	acLastUpdate =sdtf.parse(sdtf.format(new Date()));
				  	transacDate=simpleFormat.parse(simpleFormat.format(new Date()));
				  	
			    }
			catch(ParseException e) {
					e.getLocalizedMessage();
					e.printStackTrace();
			   }
			act.setUpdateDateTime(acLastUpdate);
		
		Transaction transc=new Transaction();
		transc.setActNumber(act.getActNumber());
		transc.setTxnAmount(amount);
		transc.setTxnDate(transacDate);
		transc.setTxnType("Cr");
		
		//Date date = new Date();
		Date date1=null;
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		try {
			 date1=sdf.parse(sdf.format(new Date()));
		}
		catch(ParseException e) {
			e.getLocalizedMessage();
			e.printStackTrace();
		}
		
	    transc.setTxnDate(date1);
		
		List<Transaction> traList=new ArrayList<>();
		traList.add(transc);
		act.setTransList(traList);
		ar.save(act);
		return true;
	}
	
	
	public Boolean withDraw(Double amount,Customer c) {
	
		Account act=c.getAct();
		
		Optional<Account> optAcct=ar.findById(c.getAct().getId());
		 Account getSaveAccount=null;
		 if(!optAcct.isEmpty()) {
			 getSaveAccount=optAcct.get();
		 }
		 
		 Double currBalance=getSaveAccount.getActBalance();
			if(amount>currBalance) {
			 throw new InsufficientBalanceException(getSaveAccount.getActNumber());
			}
			if(amount<=0) {
				throw new InvalidRequestException();
			}
		 act.setActBalance(getSaveAccount.getActBalance()-amount);
		 
			SimpleDateFormat sdtf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			simpleFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
			sdtf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
			
			Date acLastUpdate=null;
			Date transacDate=null;
			
			
			try {
				acLastUpdate =sdtf.parse(sdtf.format(new Date()));
				transacDate=simpleFormat.parse(simpleFormat.format(new Date()));
			}
			catch(ParseException e) {
				e.getLocalizedMessage();
				e.printStackTrace();
			}
			act.setUpdateDateTime(acLastUpdate);
		
		Transaction transc=new Transaction();
		transc.setActNumber(act.getActNumber());
		transc.setTxnAmount(amount);
		transc.setTxnDate(transacDate);
		transc.setTxnType("Dr");
		//Date date = new Date();
		Date date1=null;
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		try {
			 date1=sdf.parse(sdf.format(new Date()));
		}
		catch(ParseException e) {
			e.getLocalizedMessage();
			e.printStackTrace();
		}
		
	    transc.setTxnDate(date1);
		
		List<Transaction> traList=new ArrayList<>();
		traList.add(transc);
		act.setTransList(traList);
		ar.save(act);
		return true;	
		
	}
	
	
	public boolean impsTransfer(Account srcAct , Account destAct,Double amount) {
		
		LOG.info("Method Start: "+ "impsTransfer");
		LOG.info("User input Source Account: "+srcAct.toString());
		LOG.info(" User input Destination Account: "+destAct.toString());
		LOG.info("Amount to transfer:"+amount);
		
		Account sourceAccount=srcAct;
		Account destAccount=destAct;
		
				
		//Source account activity.
		Double sActBalance;
		sActBalance=sourceAccount.getActBalance();
		sourceAccount.setActBalance(sActBalance-amount);
		
		Transaction dEbtTransc=new Transaction();
		dEbtTransc.setActNumber(sourceAccount.getActNumber());
		dEbtTransc.setTxnAmount(amount);
		dEbtTransc.setTxnType("Dr");
		//Date date = new Date();
				Date date1=null;
				sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
				try {
					 date1=sdf.parse(sdf.format(new Date()));
				}
				catch(ParseException e) {
					e.getLocalizedMessage();
					e.printStackTrace();
				}
				
				dEbtTransc.setTxnDate(date1);
		
		
		List<Transaction> debtTransList=new ArrayList<>();
		debtTransList.add(dEbtTransc);
		
		LOG.info("Transaction 1:"+debtTransList);
		
		sourceAccount.setTransList(debtTransList);
		LOG.info("Source Account After Debit:"+sourceAccount.toString());
		
		ar.save(sourceAccount);
		
		
		
		//Destination account activity.
		Double dActBalance;
		if(destAccount.getActBalance()!=null) {
		  dActBalance=destAccount.getActBalance();
		}
		else {
			dActBalance=new Double(0);
		}
		destAccount.setActBalance(dActBalance+amount);
		
		
		
		Transaction CrTransc=new Transaction();
		CrTransc.setActNumber(destAccount.getActNumber());
		CrTransc.setTxnAmount(amount);
		CrTransc.setTxnType("Cr");
		           //date
			Date date2=null;
			sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
			try {
				date2=sdf.parse(sdf.format(new Date()));
			}
			catch(ParseException e) {
				e.getLocalizedMessage();
				e.printStackTrace();
			}
			
			CrTransc.setTxnDate(date2);
			
		List<Transaction> crtTransList=new ArrayList<>();
		crtTransList.add(CrTransc);
		LOG.info("Transaction 2:"+crtTransList);
		
		destAccount.setTransList(crtTransList);
		LOG.info("Destination Account After Credit:"+destAccount);
		ar.save(destAccount);
		
		return true;
		
	}
}
