package com.search.bo;

import java.util.ArrayList;
import java.util.List;

import com.search.exception.BusinessException;
import com.search.to.Account;

public interface AccountBO {
	public Account getAccountByUserId ( int userId ) throws BusinessException;
	public int appliedForCheckingAccount( int userId ) throws BusinessException;
	public int appliedForSavingsAccount( int userId ) throws BusinessException;
	public int createNewAccount(int id) throws BusinessException;
	public ArrayList< Account > searchAppliedAccountsChecking() throws BusinessException;
	public ArrayList<Account> searchAppliedAccountsSavings() throws BusinessException;
	public int approveAccount( Account account ) throws BusinessException;
	public int rejectAccount(Account a) throws BusinessException;
}
