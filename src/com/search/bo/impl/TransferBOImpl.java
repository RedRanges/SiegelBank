package com.search.bo.impl;

import java.util.ArrayList;

import com.search.bo.TransferBO;
import com.search.dao.TransferDAO;
import com.search.dao.impl.TransferDAOImpl;
import com.search.exception.BusinessException;
import com.search.to.Transfer;

public class TransferBOImpl implements TransferBO {
	private TransferDAO dao;
	@Override
	public boolean isRegistered( char registeredStatus ) throws BusinessException {
		return registeredStatus == 'Y';
	}
	@Override
	public int makeTransfer(int destination, int origin, double amount, int accountChoice) throws BusinessException {
		int c =0;
		String ac;
		String stringAmount = amount + "";
		if ( stringAmount.matches( "([1-9]{1}[0-9]{0,}(\\.[0-9]{0,2})?|0(\\.[0-9]{0,2})?|\\.[0-9]{1,2})$" ) ) {
			if ( accountChoice == 1) {
				ac = "checking";
			} else {
				ac = "savings";
			}
			c = getDao().makeTransfer( destination, origin, amount, ac );
		} else {
			throw new BusinessException( "Please provide amount with no dollar signs, commas and 2 or less decimals. "
					+ "Leading zeroes are okay in the case of values less than one dollar. \nAs an example 1000.50 is acceptable input." );
		}
		return c;
		
	}
	
	public TransferDAO getDao() {
		if ( dao == null ) {
			dao = new TransferDAOImpl();
		}
		return dao;
	}
	@Override
	public ArrayList<Transfer> getTransfersByUserId(int id) throws BusinessException {
		ArrayList<Transfer> transferList = new ArrayList();
		transferList = getDao().getTransfersByUserId( id );
		return transferList;
	}
	@Override
	public int setStatus(int id, char status ) throws BusinessException {
		int c = 0;
		
		c = getDao().setStatus( id, status );
		return 0;
	}
	
}
