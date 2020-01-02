package com.search.dao;

import com.search.exception.BusinessException;
import com.search.to.Transaction;

public interface TransactionsDAO {
	public Transaction makeTransaction( String type, double amount ) throws BusinessException;
}
