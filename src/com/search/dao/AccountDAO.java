package com.search.dao;

import com.search.exception.BusinessException;
import com.search.to.Account;

public interface AccountDAO {
	public Account getAccountByUserId( int userId ) throws BusinessException;
	public int appliedForCheckingAccount(int userId) throws BusinessException;
	public int appliedForSavingsAccount(int userId) throws BusinessException;
	public int createNewAccount(int id) throws BusinessException;

}
