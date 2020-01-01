package com.search.bo.impl;

import com.search.bo.UserSearchBO;
import com.search.dao.UserSearchDAO;
import com.search.dao.impl.UserSearchDaoImpl;
import com.search.exception.BusinessException;

import com.search.to.User;

public class UserSearchBoImpl implements UserSearchBO {
	private UserSearchDAO dao;
	
	@Override
	public User getUserByUsernamePassword(String username, String password) throws BusinessException {
		// TODO establish requirements for a valid username
		User user = null;
		user = getDao().getUserByUsernamePassword( username, password );
		return user;
	}
	


	@Override
	public User getUserByUsername(String username) throws BusinessException {
		User user = null;
		// TODO establish requirements for a valid username
		user = getDao().getUserByUsername( username );
		return user;
	}
	

	@Override
	public User insertUser(String username, String password) throws BusinessException {
		User user = null;
		// TODO check if password is a valid password
		if ( password.length() < 5 ) {
			throw new BusinessException( "Invalid password. Please choose a password that meets the following criteria" );	
		} else {
			user = getDao().insertUser( username, password );
		}
		return user;
	}
	
	public UserSearchDAO getDao() {
		if ( dao == null ) {
			dao = new UserSearchDaoImpl();
		}
		return dao;
	}



}
