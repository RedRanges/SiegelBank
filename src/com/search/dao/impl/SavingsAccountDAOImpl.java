package com.search.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbutil.OracleConnection;
import com.search.dao.SavingsAccountDAO;
import com.search.exception.BusinessException;
import com.search.to.CheckingAccount;
import com.search.to.SavingsAccount;

public class SavingsAccountDAOImpl implements SavingsAccountDAO {

	@Override
	public int insertSavingsAccount( double balance, int userId ) throws BusinessException {
		SavingsAccount savingsAccount = null;
		int c = 0;
		try ( Connection connection = OracleConnection.getConnection() ) {

			String sql = "insert into savings_account (balance, userid) values(?,?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement( sql );
			preparedStatement.setDouble( 1, balance );
			preparedStatement.setInt(2, userId );
			
			c =  preparedStatement.executeUpdate();
	
		} catch ( ClassNotFoundException | SQLException e ) {
			throw new BusinessException( "Internal error occured... please contact support..." + e );
		}
		return c;
	}

	@Override
	public SavingsAccount getSavingsAccountById(int userId) throws BusinessException {
		SavingsAccount savingsAccount = null;
		try ( Connection connection = OracleConnection.getConnection() ) {

			String sql = "select s.balance, s.account_number from savings_account s where s.userid=?";
			
			PreparedStatement preparedStatement = connection.prepareStatement( sql );
			preparedStatement.setInt( 1, userId );
			ResultSet resultSet = preparedStatement.executeQuery();
			if ( resultSet.next() ) {
				savingsAccount = new SavingsAccount();
				savingsAccount.setBalance( resultSet.getDouble( "balance" ) );
				savingsAccount.setAccountNumber( resultSet.getInt( "account_number") );
			}
	
		} catch ( ClassNotFoundException | SQLException e ) {
			throw new BusinessException( "Internal error occured... please contact support..." + e );
		}
		
		
		return savingsAccount;
		
	}

}
