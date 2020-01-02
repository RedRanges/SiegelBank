package com.search.dao;

import com.search.exception.BusinessException;
import com.search.to.SavingsAccount;

public interface SavingsAccountDAO {
	public int insertSavingsAccount( double balance, int userId ) throws BusinessException;
	public SavingsAccount getSavingsAccountById(int userId) throws BusinessException;
	public int updateBalance( int userId, double newBalance ) throws BusinessException;
}
