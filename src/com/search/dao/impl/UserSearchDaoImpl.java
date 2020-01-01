package com.search.dao.impl;


import com.dbutil.OracleConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.search.dao.UserSearchDAO;
import com.search.exception.BusinessException;
import com.search.to.User;



public class UserSearchDaoImpl implements UserSearchDAO {

	@Override
	public User getUserById( String id ) throws BusinessException {
		User user = null; 
		
		return user;
	}

	@Override
	public User getUserByUsernamePassword( String username, String password ) throws BusinessException {
		
		User user = null;
		try( Connection connection=OracleConnection.getConnection() ) {
			String sql = "select u.username from users u where u.username=? and u.password=?";
			
			PreparedStatement preparedStatement=connection.prepareStatement( sql );
			preparedStatement.setString( 1, username );
			preparedStatement.setString( 2,  password );
			ResultSet resultSet = preparedStatement.executeQuery();
			if( resultSet.next() ) {
				user = new User();
				user.setUsername( resultSet.getString( "username" ) );
			}else {
				throw new BusinessException( "Please check username and password" );
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException( "Internal error occured..please contact support..."+ e );
		}
		return user;
	}

}