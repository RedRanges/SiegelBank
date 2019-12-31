package com.search.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dbutil.OracleConnection;
import com.search.dao.CustomerSearchDAO;
import com.search.exception.BusinessException;
import com.search.to.Customer;


public class CustomerSearchDaoImpl implements CustomerSearchDAO {

	@Override
	public Customer getCustomerById( String id ) throws BusinessException {
		Customer Customer=null;
		try( Connection connection=OracleConnection.getConnection() ){

			String sql = "select c.id, c.LAST_NAME, c.FIRST_NAME, c.dob, c.USERNAME, c.registered from "
					+ "Customers c where c.id =?";
			
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt( 1, Integer.parseInt( id ) );
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if( resultSet.next() ) {
				Customer = new Customer();
				Customer.setId( Integer.parseInt( id ) );
				Customer.setLastName( resultSet.getString("last_Name") );
				Customer.setFirstName( resultSet.getString("first_Name") );
				Customer.setDob( resultSet.getDate("dob") );
				Customer.setUsername( resultSet.getString("username") );
				Customer.setRegistered( resultSet.getString("registered").charAt( 0 ) );
			}else {
				throw new BusinessException("No customer with matching id : " + id );
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException( "Internal error occured..please contact support..."+ e );
		}
		return Customer;
	}

	

	@Override
	public List<Customer> getCustomersByDob(Date dob) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getCustomersByRegistered( char registered ) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getCustomerByUsername( String teamName ) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Customer getCustomerByUsernamePassword(String username, String password) throws BusinessException{
		Customer Customer=null;
		try( Connection connection=OracleConnection.getConnection() ) {
			String sql = "select c.id, c.LAST_NAME, c.FIRST_NAME, c.dob, c.USERNAME, c.registered from "
					+ "Customers c where c.username=?";
			
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString( 1, username );
			ResultSet resultSet = preparedStatement.executeQuery();
			if( resultSet.next() ) {
				Customer = new Customer();
				Customer.setId(resultSet.getInt( "id" ) );
				Customer.setLastName( resultSet.getString("last_Name") );
				Customer.setFirstName( resultSet.getString("first_Name") );
				Customer.setDob( resultSet.getDate("dob") );
				Customer.setUsername( resultSet.getString("username") );
				Customer.setRegistered( resultSet.getString("registered").charAt( 0 ) );
			}else {
				throw new BusinessException("No customer with matching username : " + username );
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException( "Internal error occured..please contact support..."+ e );
		}
		return Customer;
	}

}
