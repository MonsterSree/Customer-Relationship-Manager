package com.srikanth.springdemo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.srikanth.springdemo.entity.Customer;
import com.srikanth.springdemo.util.SortUtils;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		Session session=sessionFactory.getCurrentSession();
		
		Query query =session.createQuery("from Customer order by lastName",Customer.class);
		
		List<Customer> customers=query.getResultList();
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomer(int theId) {
		Session session=sessionFactory.getCurrentSession();
		
		return session.get(Customer.class, theId);
	}

	@Override
	public void delete(int theId) {
		Session session=sessionFactory.getCurrentSession();
		Query theQuery=session.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
		
	}

	@Override
	public List<Customer> searchCustomer(String name) {
		Session session=sessionFactory.getCurrentSession();
		Query query=null;
		
		 if (name != null && name.trim().length() > 0) {
	           
	          query =session.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
	          query.setParameter("theName", "%" + name.toLowerCase() + "%");
		 }
		 else {
			 query =session.createQuery("from Customer");
			 
		 }
		 List<Customer> customers = query.getResultList();
		 
		 return customers;
	}

	@Override
	public List<Customer> getCustomers(int theSortField) {
		Session session=sessionFactory.getCurrentSession();
		String fieldName=null;
		switch(theSortField) {
		case SortUtils.FIRST_NAME:
			fieldName="firstName";
			break;
		case SortUtils.LAST_NAME:
			fieldName="lastName";
			break;
		case SortUtils.EMAIL:
			fieldName="email";
			break;
		default:
			fieldName="lastName";
		}
		
		Query<Customer> query=session.createQuery("from Customer order by "+fieldName,Customer.class);
		List<Customer> list=query.getResultList();
		return list;
	}
	

}
