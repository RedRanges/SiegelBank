package com.search.dao.impl;

import com.search.exception.BusinessException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbutil.OracleConnection;
import com.search.dao.CheckingAccountDAO;
import com.search.to.CheckingAccount;

public class CheckingAccountDAOImpl implements CheckingAccountDAO {

	@Override
	public int insertCheckingAccount( double balance, int userId ) throws BusinessException {
//		CheckingAccount checkingAccount = null;
		int c = 0;
		try ( Connection connection = OracleConnection.getConnection() ) {

			String sql = "insert into checking_account (balance, userid) values(?,?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement( sql );
			preparedStatement.setDouble( 1, balance );
			preparedStatement.setInt(2, userId );
			
			c =  preparedStatement.executeUpdate();
//			CallableStatement callable;
//			callable = connection.prepareCall( "Call update_account_table" );
	
		} catch ( ClassNotFoundException | SQLException e ) {
			throw new BusinessException( "Internal error occured... please contact support..." + e );
		}
		
		return c;
	}

	@Override
	public CheckingAccount getCheckingAccountById ( int userId ) throws BusinessException {
		CheckingAccount checkingAccount = null;
		try ( Connection connection = OracleConnection.getConnection() ) {

			String sql = "select c.balance, c.account_number from checking_account c where c.userid=?";
			
			PreparedStatement preparedStatement = connection.prepareStatement( sql );
			preparedStatement.setInt( 1, userId );
			ResultSet resultSet = preparedStatement.executeQuery();
			if ( resultSet.next() ) {
				checkingAccount = new CheckingAccount();
				checkingAccount.setBalance( resultSet.getDouble( "balance" ) );
				checkingAccount.setAccountNumber( resultSet.getInt( "account_number") );
			}
	
		} catch ( ClassNotFoundException | SQLException e ) {
			throw new BusinessException( "Internal error occured... please contact support..." + e );
		}
		
		
		return checkingAccount;
	}

	@Override
	public int updateBalance( int userId, double newBalance ) throws BusinessException {
		int c = 0;
		try ( Connection connection = OracleConnection.getConnection() ) {

			String sql = "update checking_account set balance=? where userid=?";
			PreparedStatement preparedStatement = connection.prepareStatement( sql );
			preparedStatement.setDouble( 1, newBalance );
			preparedStatement.setInt( 2, userId );
			
			c =  preparedStatement.executeUpdate();
	
		} catch ( ClassNotFoundException | SQLException e ) {
			throw new BusinessException( "Internal error occured... please contact support..." + e );
		}
		return c;
	}

}
