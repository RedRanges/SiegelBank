package com.search.bo;

import com.search.exception.BusinessException;
import com.search.to.CheckingAccount;

public interface CheckingAccountBO {
	public int insertCheckingAccount( String stringBalance, int userId ) throws BusinessException;
	public CheckingAccount getCheckingAccountById( int userId ) throws BusinessException;

}
