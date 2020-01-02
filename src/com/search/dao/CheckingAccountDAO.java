package com.search.dao;

import com.search.exception.BusinessException;
import com.search.to.CheckingAccount;

public interface CheckingAccountDAO {
public int insertCheckingAccount( double balance, int userId ) throws BusinessException;
public CheckingAccount getCheckingAccountById( int userId ) throws BusinessException;
}
