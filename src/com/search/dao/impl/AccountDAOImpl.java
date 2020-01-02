package com.search.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbutil.OracleConnection;
import com.search.dao.AccountDAO;
import com.search.exception.BusinessException;
import com.search.to.Account;
import com.search.to.User;


public class AccountDAOImpl implements AccountDAO {

	@Override
	public Account getAccountByUserId( int userId ) throws BusinessException {
		Account account = null;
		try ( Connection connection = OracleConnection.getConnection() ) {
			String sql = "select a.type, a.checking_applied, a.checking_registered, a.saving_registered, a.saving_applied from accounts a where userid=?";
			
			PreparedStatement preparedStatement = connection.prepareStatement( sql );
			preparedStatement.setInt( 1, userId );
			ResultSet resultSet = preparedStatement.executeQuery();
			if ( resultSet.next() ) {
				account = new Account();
				account.setType( resultSet.getString( "type" ) );
				account.setAppliedChecking( resultSet.getString( "checking_applied" ).charAt( 0 ) );
				account.setRegisteredChecking( resultSet.getString( "checking_registered" ).charAt( 0 ) );
				account.setAppliedSavings( resultSet.getString( "saving_applied" ).charAt( 0 ) );
				account.setRegisteredSavings( resultSet.getString( "saving_registered" ).charAt( 0 ) );
			} else {
				throw new BusinessException( "No matching USERID found" );
			}	
		} catch ( ClassNotFoundException | SQLException e ) {
			throw new BusinessException( "Internal error occured... please contact support..." + e );
		}
		return account;
	}

	@Override
	public int appliedForCheckingAccount( int userId ) throws BusinessException {
		int c = 0;
		try ( Connection connection = OracleConnection.getConnection() ){
			String sql = "update accounts set checking_applied='Y' where userid = ?";
			PreparedStatement preparedStatement = connection.prepareStatement( sql );
			preparedStatement.setInt( 1, userId );
			c = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException ( "Internal Error please contact support." );
		} 
		return c;
	}

	@Override
	public int appliedForSavingsAccount(int userId) throws BusinessException {
		int c = 0;
		try ( Connection connection = OracleConnection.getConnection() ){
			String sql = "update accounts set saving_applied='Y' where userid = ?";
			PreparedStatement preparedStatement = connection.prepareStatement( sql );
			preparedStatement.setInt( 1, userId );
			c = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException ( "Internal Error please contact support." );
		} 
		return c;
	}

	@Override
	public int createNewAccount(int userId ) throws BusinessException {
		int c = 0;
		try ( Connection connection = OracleConnection.getConnection() ){
			String sql = "insert into accounts ( userid ) values (?)";
			PreparedStatement preparedStatement = connection.prepareStatement( sql );
			preparedStatement.setInt( 1, userId );
			c = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException ( "Internal Error please contact support." );
		} 
		return c;
	}

}
