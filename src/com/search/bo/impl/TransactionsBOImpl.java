package com.search.bo.impl;

import com.search.bo.TransactionsBO;
import com.search.dao.TransactionsDAO;
import com.search.dao.impl.TransactionsDAOImpl;
import com.search.exception.BusinessException;
import com.search.to.Transaction;

public class TransactionsBOImpl implements TransactionsBO {
	private TransactionsDAO dao;
	@Override
	public Transaction makeTransaction(String type, double amount) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public TransactionsDAO getDao() {
		if ( dao == null ) {
			dao = new TransactionsDAOImpl();
		}
		return dao;
	}
	
	

}
