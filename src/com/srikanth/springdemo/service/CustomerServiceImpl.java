package com.srikanth.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.srikanth.springdemo.dao.CustomerDAO;
import com.srikanth.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		return customerDAO.getCustomers();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {
		customerDAO.saveCustomer(theCustomer);
		
	}

	@Override
	@Transactional
	public Customer getCustomer(int theId) {
		
		return customerDAO.getCustomer(theId);
	}

	@Override
	@Transactional
	public void delete(int theId) {
		customerDAO.delete(theId);
		
	}

	@Override
	@Transactional
	public List<Customer> searchCustomer(String name) {
		
		return customerDAO.searchCustomer(name);
	}

	@Override
	@Transactional
	public List<Customer> getCustomers(int theSortField) {
		
		return customerDAO.getCustomers(theSortField);
	}

	
	
	
	
	
	

}
