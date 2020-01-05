package com.search.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dbutil.OracleConnection;
import com.search.dao.TransferDAO;
import com.search.exception.BusinessException;
import com.search.to.Transfer;

public class TransferDAOImpl implements TransferDAO {

	@Override
	public int makeTransfer(int destination, int origin, double amount, String accountChoice) throws BusinessException {
		int c = 0;
		try ( Connection connection = OracleConnection.getConnection() ) {
			
			String sql = "insert into transfers ( destination, origin, amount, account_type ) values(?,?,?,?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement( sql );
			preparedStatement.setInt( 1, destination );
			preparedStatement.setInt(2, origin );
			preparedStatement.setDouble(3,  amount);
			preparedStatement.setString( 4, accountChoice );
			
			c =  preparedStatement.executeUpdate();
	
		} catch ( ClassNotFoundException | SQLException e ) {
			throw new BusinessException( "Internal error occured... please contact support..." + e );
		}
		
		return c;
	}

	@Override
	public ArrayList<Transfer> getTransfersByUserId(int id) throws BusinessException {
		ArrayList transferList = new ArrayList();
		try ( Connection connection = OracleConnection.getConnection() ) {
					
					String sql = "select t.id, t.amount, t.status, t.origin from transfers t where t.destination=? and t.status='P'";
					
					PreparedStatement preparedStatement = connection.prepareStatement( sql );
					
					preparedStatement.setInt( 1, id );
				
					
					preparedStatement.executeQuery();
					ResultSet resultSet = preparedStatement.executeQuery( sql );
					if ( resultSet.next() ) {
						Transfer transfer = new Transfer();
						transfer.setAmount( resultSet.getDouble( "amount" ) );
						transfer.setStatus( resultSet.getString( "status" ).charAt( 0 ) );
						transfer.setOrigin( resultSet.getInt( "origin" ) );
						transfer.setId( resultSet.getInt( "id" ) );
						transferList.add( transfer );
					}
			
				} catch ( ClassNotFoundException | SQLException e ) {
					throw new BusinessException( "Internal error occured... please contact support..." + e );
				}
				
				return transferList;
	}

	@Override
	public int setStatus(int id, char status ) throws BusinessException {
		int c = 0;
try ( Connection connection = OracleConnection.getConnection() ) {
			String sql;
			if ( status == 'A' ) {
				sql = "update transfers set status = 'A' where id=?";
			} else {
				sql = "update transfers set status = 'R' where id=?";
			}
			
			PreparedStatement preparedStatement = connection.prepareStatement( sql );
			
			preparedStatement.setInt(1, id );
			
			
			c =  preparedStatement.executeUpdate();
	
		} catch ( ClassNotFoundException | SQLException e ) {
			throw new BusinessException( "Internal error occured... please contact support..." + e );
		}
		return c;
	}
	
}
