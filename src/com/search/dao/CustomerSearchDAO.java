package com.search.dao;

import java.util.Date;
import java.util.List;

import com.search.exception.BusinessException;
import com.search.to.Customer;

public interface CustomerSearchDAO {
	public Customer getCustomerById(String id) throws BusinessException;
	public List< Customer > getCustomersByDob(Date dob) throws BusinessException;
	public List< Customer > getCustomersByRegistered(char registered) throws BusinessException;
	public List< Customer > getCustomerByUsername(String teamName) throws BusinessException;
}
