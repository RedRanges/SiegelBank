package com.search.bo;

import com.search.exception.BusinessException;
import com.search.to.Transaction;

public interface TransactionsBO {

	public Transaction makeTransaction( String type, double amount ) throws BusinessException;

}
