package com.search.bo;

import java.util.ArrayList;

import com.search.exception.BusinessException;
import com.search.to.Transfer;

public interface TransferBO {

	public boolean isRegistered( char registeredStatus ) throws BusinessException;

	public int makeTransfer(int destination, int origin, double amount, int accountChoice) throws BusinessException;

	public ArrayList<Transfer> getTransfersByUserId(int id) throws BusinessException;

	public int setStatus(int id, char status ) throws BusinessException;

}
