package com.search.bo.impl;

import com.search.bo.TransactionsBO;
import com.search.dao.TransactionsDAO;
import com.search.dao.impl.TransactionsDAOImpl;
import com.search.exception.BusinessException;
import com.search.to.Transaction;

public class TransactionsBOImpl implements TransactionsBO {
	private TransactionsDAO dao;
	@Override
	public Transaction makeTransaction( String type, double amount, double balance, int userId ) throws BusinessException {
		Transaction transaction = null;
		if ( amount <= 0 ) {
			throw new BusinessException ( "Please enter an amount greater than $0" );
		}
		
		if ( type.equals( "withdraw" ) ) {
			if ( amount > balance ) {
				throw new BusinessException( "You don't have enough money to withdraw that amount" );
			}
		} 
			
		transaction = getDao().makeTransaction( type, amount, userId );
		
		return transaction;
	}
	public TransactionsDAO getDao() {
		if ( dao == null ) {
			dao = new TransactionsDAOImpl();
		}
		return dao;
	}
	
	

}
