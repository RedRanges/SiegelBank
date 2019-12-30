package com.search.bo;

import java.util.List;

import com.search.exception.BusinessException;
import com.search.to.Customer;


public interface CustomerSearchBO {
	
	public Customer getCustomerById(String id) throws BusinessException;
	public List< Customer > getCustomersByDob(String dob) throws BusinessException;
	public List< Customer > getCustomersByRegistered(char registered) throws BusinessException;
	public List< Customer > getCustomerByUsername(String teamName) throws BusinessException;

}
