package com.search.dao;

import com.search.exception.BusinessException;
import com.search.to.User;

public interface UserDAO {
	public User getUserId( String username ) throws BusinessException;
	public User getUserByUsernamePassword( String username, String password ) throws BusinessException;
	public User getUserByUsername(String username) throws BusinessException;
	public User insertUser(String username, String password) throws BusinessException;
	
}

