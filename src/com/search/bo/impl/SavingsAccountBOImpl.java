package com.search.bo.impl;


import com.search.bo.SavingsAccountBO;
import com.search.dao.SavingsAccountDAO;

import com.search.dao.impl.SavingsAccountDAOImpl;
import com.search.exception.BusinessException;
import com.search.to.SavingsAccount;

public class SavingsAccountBOImpl implements SavingsAccountBO {
	private SavingsAccountDAO dao;
	
	@Override
	public int insertSavingsAccount( String stringBalance, int userId ) throws BusinessException {
		int c = 0;
		if ( stringBalance.matches( "([1-9]{1}[0-9]{0,}(\\.[0-9]{0,2})?|0(\\.[0-9]{0,2})?|\\.[0-9]{1,2})$" ) ) {
			double balance = Double.parseDouble( stringBalance );
			c = getDao().insertSavingsAccount( balance, userId );
		} else {
			throw new BusinessException( "Please provide amount with no dollar signs, commas and 2 or less decimals. "
					+ "Leading zeroes are okay in the case of values less than one dollar. \nAs an example 1000.50 is acceptable input." );
		}
		return c;
	}
	

	@Override
	public SavingsAccount getSavingsAccountById(int userId) throws BusinessException {
		SavingsAccount savingsAccount = null;
		savingsAccount = getDao().getSavingsAccountById( userId );
		
		return savingsAccount;
	}
	
	@Override
	public int updateBalance(int userId, String type, double delta, double balance) throws BusinessException {
		int c = 0;
		
		if ( type.equals( "withdraw" ) ) {
			delta = delta * -1;
		}
		
		double newBalance = balance + delta;
		c = getDao().updateBalance( userId, newBalance );
		
		
		return c;
	}
	
	public SavingsAccountDAO getDao() {
		if ( dao == null ) {
			dao = new SavingsAccountDAOImpl();
		}
		return dao;
	}





}
