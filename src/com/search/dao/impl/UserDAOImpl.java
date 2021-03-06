package com.search.dao.impl;


import com.dbutil.OracleConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.search.dao.UserDAO;
import com.search.exception.BusinessException;
import com.search.to.User;



public class UserDAOImpl implements UserDAO {

	@Override
	public User getUserId( String username ) throws BusinessException {
		User user = null; 
		try( Connection connection = OracleConnection.getConnection() ) {
		String sql = "select u.username, u.id from users u where u.username=?";
		PreparedStatement preparedStatement = connection.prepareStatement( sql );
		preparedStatement.setString( 1, username );
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if ( resultSet.next() ) {
			user = new User();
			user.setUsername( resultSet.getString( "username" ) );
			user.setId( resultSet.getInt( "id") );
		} else {
			throw new BusinessException( "Unable to find user" );
		}
		} catch ( ClassNotFoundException | SQLException e ) {
			throw new BusinessException( "contact support "  + e );
		}
		
		return user;
	}

	@Override
	public User getUserByUsernamePassword( String username, String password ) throws BusinessException {
		
		User user = null;
		try( Connection connection = OracleConnection.getConnection() ) {
			String sql = "select u.username, u.id from users u where u.username=? and u.password=?";
								
			PreparedStatement preparedStatement=connection.prepareStatement( sql );
			preparedStatement.setString( 1, username );
			preparedStatement.setString( 2,  password );
			ResultSet resultSet = preparedStatement.executeQuery();
			if( resultSet.next() ) {
				user = new User();
				user.setUsername( resultSet.getString( "username" ) );
				user.setId( resultSet.getInt( "id" ) );
			}else {
				throw new BusinessException( "Please check username and password" );
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException( "Internal error occured..please contact support..."+ e );
		}
		return user;
	}

	@Override
	public User getUserByUsername( String username ) throws BusinessException {
		User user = null;
		try ( Connection connection = OracleConnection.getConnection() ) {
			String sql = "select u.username from users u where u.username=?";
			
			PreparedStatement preparedStatement = connection.prepareStatement( sql );
			preparedStatement.setString( 1, username );
			ResultSet resultSet = preparedStatement.executeQuery();
			if ( resultSet.next() ) {
				throw new BusinessException( "Username already exists. Please select a different username : " );
			} else {
				
			}
			
		} catch ( ClassNotFoundException | SQLException e ) {
			throw new BusinessException( "Internal error occured... please contact support..." + e );
		}
		return null;
	}
	
	// TODO change return type for inserts?
	@Override
	public User insertUser( String username, String password ) throws BusinessException {
		User user = null;
		try ( Connection connection = OracleConnection.getConnection() ) {
			String sql = "insert into users (username, password) values (?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement( sql );
			preparedStatement.setString( 1, username );
			preparedStatement.setString( 2, password );
			
			preparedStatement.executeUpdate();
		} catch ( ClassNotFoundException | SQLException e ) {
			throw new BusinessException( "Internal error occured ... please contact support ... " + e );
		}
		return null;
	}

	@Override
	public User findUser(String username) throws BusinessException {
		
		try ( Connection connection = OracleConnection.getConnection() ) {
			String sql = "select u.username, u.id from users u where u.username=?";
			
			PreparedStatement preparedStatement = connection.prepareStatement( sql );
			preparedStatement.setString( 1, username );
			ResultSet resultSet = preparedStatement.executeQuery();
			if ( resultSet.next() ) {
				User user = new User();
				user.setId( resultSet.getInt( "id" ) );
				user.setUsername( resultSet.getString( "username" ) );
				return user;
			} else {
				throw new BusinessException( "User does not exist" );
			}
			
		} catch ( ClassNotFoundException | SQLException e ) {
			throw new BusinessException( "Internal error occured... please contact support..." + e );
		}
		
	}

	@Override
	public User getUserById(int id) throws BusinessException {
		User user = null;
		try ( Connection connection = OracleConnection.getConnection() ) {
			String sql = "select username from users where id=?";
			PreparedStatement preparedStatement = connection.prepareStatement( sql );
			preparedStatement.setInt( 1, id );
			
			
			preparedStatement.executeUpdate();
		} catch ( ClassNotFoundException | SQLException e ) {
			throw new BusinessException( "Internal error occured ... please contact support ... " + e );
		}
		
		return user;
		
	}

}
