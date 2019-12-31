package com.search.bo.impl;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.search.bo.CustomerSearchBO;
import com.search.exception.BusinessException;
import com.search.dao.CustomerSearchDAO;
import com.search.dao.impl.CustomerSearchDaoImpl;
import com.search.to.Customer;

public class CustomerSearchBoImpl implements CustomerSearchBO {
	private CustomerSearchDAO dao;
	
	@Override
	public Customer getCustomerById(String id) throws BusinessException {
		Customer customer = null;
		// determine code for checking valid id
		customer = getDao().getCustomerById( id );
		return customer;
	}

	
	@Override
	public List<Customer> getCustomersByDob(String dob) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getCustomersByRegistered(char registered) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getCustomerByUsername(String teamName) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Customer getCustomerByUsernamePassword( String username, String password ) throws BusinessException {
		// TODO Auto-generated method stub
		Customer customer = null;
		customer = getDao().getCustomerByUsernamePassword( username, password );
		return customer;
	}
	
	
	public CustomerSearchDAO getDao() {
		if ( dao == null ) {
			dao = new CustomerSearchDaoImpl();
		}
		return dao;
	}
	
}
