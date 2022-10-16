package com.srikanth.springdemo.service;

import java.util.List;

import com.srikanth.springdemo.entity.Customer;

public interface CustomerService {
	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public void delete(int theId);


	public List<Customer> searchCustomer(String name);

	public List<Customer> getCustomers(int theSortField);
}
