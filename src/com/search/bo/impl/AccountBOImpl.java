package com.search.bo.impl;

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
	
	public AccountDAO getDao() {
		if ( dao == null ) {
			dao = new AccountDAOImpl();
		}
		return dao;
	}




	
}
