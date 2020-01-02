package com.search.bo;

import com.search.exception.BusinessException;
import com.search.to.SavingsAccount;

public interface SavingsAccountBO {
	public int insertSavingsAccount( String stringBalance, int userId ) throws BusinessException;
	public SavingsAccount getSavingsAccountById(int id) throws BusinessException;
	public int updateBalance ( int userId, String type, double delta, double balance ) throws BusinessException;
}
