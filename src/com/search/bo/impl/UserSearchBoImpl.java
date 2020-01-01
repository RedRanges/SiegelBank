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
		User user = null;
		user = getDao().getUserByUsernamePassword( username, password );
		return user;
	}
	
	public UserSearchDAO getDao() {
		if ( dao == null ) {
			dao = new UserSearchDaoImpl();
		}
		return dao;
	}

}
