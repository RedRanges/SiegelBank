package com.search.bo.impl;

import com.search.bo.UserBO;
import com.search.dao.UserDAO;
import com.search.dao.impl.UserDAOImpl;
import com.search.exception.BusinessException;

import com.search.to.User;

public class UserBOImpl implements UserBO {
	private UserDAO dao;
	
	@Override
	public User getUserByUsernamePassword(String username, String password) throws BusinessException {
		// TODO establish requirements for a valid username
		// * case insensitive 
		// * length between x and y
		// * only valid characters
		User user = null;
		user = getDao().getUserByUsernamePassword( username, password );
		return user;
	}
	


	@Override
	public User getUserByUsername(String username) throws BusinessException {
		User user = null;
		// TODO establish requirements for a valid username
		// * case insensitive 
		// * length between x and y
		// * only valid characters
		user = getDao().getUserByUsername( username );
		return user;
	}
	

	@Override
	public User insertUser(String username, String password) throws BusinessException {
		User user = null;
		// TODO check if password is a valid password
		// * case sensitive
		// * length between x and y
		// * only valid characters
		if ( password.length() < 5 ) {
			throw new BusinessException( "Invalid password. Please choose a password that meets the following criteria" );	
		} else {
			user = getDao().insertUser( username, password );
		}
		return user;
	}
	
	public UserDAO getDao() {
		if ( dao == null ) {
			dao = new UserDAOImpl();
		}
		return dao;
	}



}
