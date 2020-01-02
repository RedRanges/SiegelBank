package com.search.dao;

import java.util.ArrayList;
import java.util.List;

import com.search.exception.BusinessException;
import com.search.to.Account;

public interface AccountDAO {
	public Account getAccountByUserId( int userId ) throws BusinessException;
	public int appliedForCheckingAccount(int userId) throws BusinessException;
	public int appliedForSavingsAccount(int userId) throws BusinessException;
	public int createNewAccount(int id) throws BusinessException;
	public ArrayList<Account> searchAppliedAccountsSavings() throws BusinessException;
	public ArrayList<Account> searchAppliedAccountsChecking() throws BusinessException;
	public int approveAccount(int userId, String accountType ) throws BusinessException;
	public int rejectAccount(int userId, String accountType ) throws BusinessException;

}
