package com.search.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dbutil.OracleConnection;
import com.search.dao.TransactionsDAO;
import com.search.exception.BusinessException;
import com.search.to.Transaction;


public class TransactionsDAOImpl implements TransactionsDAO {

	@Override
	public Transaction makeTransaction( String type, double amount, int userId ) throws BusinessException {
		Transaction transaction = null;
		int c;
		try ( Connection connection = OracleConnection.getConnection() ) {
			String sql = "insert into transactions (type, amount, userId) values(?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement( sql );
			preparedStatement.setString( 1, type );
			preparedStatement.setDouble(2, amount );
			preparedStatement.setInt( 3,  userId );
			
			c =  preparedStatement.executeUpdate();
			if ( c == 1 ) {
				transaction = new Transaction();
				transaction.setType( type );
				transaction.setAmount( amount );
				return transaction;
			}
			return null;
			
		} catch( ClassNotFoundException | SQLException e ) {
			throw new BusinessException( "please contact support"  + e );
		}
		
	}

}
