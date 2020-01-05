package com.search.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	@Override
	public ArrayList<Account> searchAppliedAccountsChecking() throws BusinessException {
		ArrayList <Account> accountList = null;
		try ( Connection connection = OracleConnection.getConnection() ) {
			accountList = new ArrayList();
			String sql = "select a.userid, a.checking_applied, a.checking_registered from accounts a where (a.checking_registered='N') and (a.checking_applied='Y')";
			PreparedStatement preparedStatement = connection.prepareStatement( sql );
			ResultSet resultSet = preparedStatement.executeQuery();
			if ( resultSet.next() ) {
				Account account = new Account();
				account.setUserId( resultSet.getInt( "userid" ) );
				account.setType( "Customer" );
				account.setAppliedChecking( resultSet.getString("checking_applied").charAt( 0 ) );
				account.setRegisteredChecking( resultSet.getString("checking_registered").charAt( 0 ) );
				
				
				accountList.add( account );
				
			} else {
				throw new BusinessException( "No checking accounts found" );
			}	
		} catch ( ClassNotFoundException | SQLException e ) {
			throw new BusinessException( "Internal error occured... please contact support..." + e );
		}
		return  accountList;
	}

	@Override
	public ArrayList<Account> searchAppliedAccountsSavings() throws BusinessException {
		ArrayList <Account> accountList = null;
		try ( Connection connection = OracleConnection.getConnection() ) {
			accountList = new ArrayList();
			String sql = "select a.userid, a.saving_applied, a.saving_registered from accounts a where ( a.saving_registered='N') and (a.saving_applied='Y')";
			PreparedStatement preparedStatement = connection.prepareStatement( sql );
			ResultSet resultSet = preparedStatement.executeQuery();
			if ( resultSet.next() ) {
				Account account = new Account();
				account.setUserId( resultSet.getInt( "userid" ) );
				account.setType( "Customer" );
				account.setAppliedSavings( resultSet.getString("saving_applied").charAt( 0 ) );
				account.setRegisteredSavings( resultSet.getString("saving_registered").charAt( 0 ) );
				
				accountList.add( account );
				
			} else {
				throw new BusinessException( "No savings accounts found" );
			}
				
		} catch ( ClassNotFoundException | SQLException e ) {
			throw new BusinessException( "Internal error occured... please contact support..." + e );
		}
		return  accountList;
	}

	@Override
	public int approveAccount(int userId, String accountType) throws BusinessException {
		int c = 0;
		try ( Connection connection = OracleConnection.getConnection() ){
			String sql = "update accounts set " + accountType+"='Y' where userid = ?";
			PreparedStatement preparedStatement = connection.prepareStatement( sql );
			preparedStatement.setInt( 1, userId );
			
			c = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException ( "Internal Error please contact support." + e );
		} 
		return c;
		
	}

	@Override
	public int rejectAccount(int userId, String accountType) throws BusinessException {
		int c = 0;
		try ( Connection connection = OracleConnection.getConnection() ){
			String sql = "update accounts set " + accountType+"='R' where userid = ?";
			PreparedStatement preparedStatement = connection.prepareStatement( sql );
			preparedStatement.setInt( 1, userId );
			
			c = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException ( "Internal Error please contact support." + e );
		} 
		return c;
	}

}
