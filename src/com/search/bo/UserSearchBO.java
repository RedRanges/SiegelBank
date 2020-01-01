package com.search.bo;

import com.search.exception.BusinessException;
import com.search.to.User;

public interface UserSearchBO {
	public User getUserByUsernamePassword(String username, String password) throws BusinessException;
}
