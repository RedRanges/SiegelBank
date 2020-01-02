package com.search.bo.impl;

import java.util.ArrayList;
import java.util.List;

import com.search.bo.AccountBO;
import com.search.dao.AccountDAO;
import com.search.dao.impl.AccountDAOImpl;
import com.search.exception.BusinessException;
import com.search.to.Account;

public class AccountBOImpl implements AccountBO {
	private AccountDAO dao;
	
	@Override
	public Account getAccountByUserId( int userId ) throws BusinessException {
		Account account = null; 
		account = getDao().getAccountByUserId( userId );
		return account;
	}
	
	@Override
	public int appliedForCheckingAccount ( int userId ) throws BusinessException {
		int c = 0;
		c = getDao().appliedForCheckingAccount( userId );
		return c;
	}
	
	@Override
	public int appliedForSavingsAccount(int userId) throws BusinessException {
		// TODO Auto-generated method stub
		int c = 0;
		c = getDao().appliedForSavingsAccount( userId );
		return c;
	}
	
	@Override
	public int createNewAccount(int id) throws BusinessException {
		int c;
		c = getDao().createNewAccount( id );
		return c;
		
		
	}
	
	@Override
	public ArrayList<Account> searchAppliedAccountsChecking() throws BusinessException {
		ArrayList <Account> appliedList = getDao().searchAppliedAccountsChecking();
		return appliedList;
	}
	@Override
	public ArrayList<Account> searchAppliedAccountsSavings() throws BusinessException {
		ArrayList <Account> appliedList = getDao().searchAppliedAccountsSavings();
		return appliedList;
	}
	
	@Override
	public int approveAccount(Account account) throws BusinessException {
		int c = 0;
		if ( account.getAppliedChecking() == 'Y' ) {
			c = getDao().approveAccount( account.getUserId(), "checking_registered" );
		} else {
			c = getDao().approveAccount( account.getUserId() , "saving_registered" );
		}
		
		return c;
	}
	
	@Override
	public int rejectAccount( Account account ) throws BusinessException {
		int c = 0;
		if ( account.getAppliedChecking() == 'Y' ) {
			c = getDao().rejectAccount( account.getUserId(), "checking_registered" );
		} else {
			c = getDao().rejectAccount( account.getUserId() , "saving_registered" );
		}
		
		return c;
	}
	
	public AccountDAO getDao() {
		if ( dao == null ) {
			dao = new AccountDAOImpl();
		}
		return dao;
	}

	

	

	

	




	
}
