package com.search.bo.impl;

import com.search.bo.CheckingAccountBO;
import com.search.dao.AccountDAO;
import com.search.dao.CheckingAccountDAO;
import com.search.dao.impl.AccountDAOImpl;
import com.search.dao.impl.CheckingAccountDAOImpl;
import com.search.exception.BusinessException;
import com.search.to.CheckingAccount;

public class CheckingAccountBOImpl implements CheckingAccountBO {
	private CheckingAccountDAO dao;
	
	@Override
	public int insertCheckingAccount( String stringBalance, int userId ) throws BusinessException {
		int c = 0;
		if ( stringBalance.matches( "([1-9]{1}[0-9]{0,}(\\.[0-9]{0,2})?|0(\\.[0-9]{0,2})?|\\.[0-9]{1,2})$" ) ) {
			double balance = Double.parseDouble( stringBalance );
//			CheckingAccount checkingAccount = null;
			c = getDao().insertCheckingAccount( balance, userId );
		} else {
			throw new BusinessException( "Please provide amount with no dollar signs, commas and 2 or less decimals. "
					+ "Leading zeroes are okay in the case of values less than one dollar. \nAs an example 1000.50 is acceptable input." );
		}
		return c;
	}
	
	@Override
	public CheckingAccount getCheckingAccountById( int userId ) throws BusinessException {
		CheckingAccount checkingAccount = null;
		checkingAccount = getDao().getCheckingAccountById( userId );
		return checkingAccount;
	}
	
	public CheckingAccountDAO getDao() {
		if ( dao == null ) {
			dao = new CheckingAccountDAOImpl();
		}
		return dao;
	}



}
