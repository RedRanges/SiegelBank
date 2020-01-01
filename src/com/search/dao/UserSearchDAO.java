package com.search.dao;
import java.util.List;

import com.search.exception.BusinessException;
import com.search.to.User;

public interface UserSearchDAO {
	public User getUserById( String id ) throws BusinessException;
	public User getUserByUsernamePassword( String username, String password ) throws BusinessException;
	
}

