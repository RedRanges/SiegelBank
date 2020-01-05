package com.search.dao;

import java.util.ArrayList;

import com.search.exception.BusinessException;
import com.search.to.Transfer;

public interface TransferDAO {

	public int makeTransfer(int destination, int origin, double amount, String accountChoice) throws BusinessException;

	public ArrayList<Transfer> getTransfersByUserId(int id) throws BusinessException;

	public int setStatus(int id, char c) throws BusinessException;

}
