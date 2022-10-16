package com.srikanth.springdemo.controller;

import java.util.List;import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.srikanth.springdemo.entity.Customer;
import com.srikanth.springdemo.service.CustomerService;
import com.srikanth.springdemo.util.SortUtils;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService; 
	
	@GetMapping("/list")
	public String listCustomers(Model model,@RequestParam(required=false) String sort) {
		
		List<Customer> theCustomers=null;
		
		if (sort != null) {
			int theSortField = Integer.parseInt(sort);
			theCustomers = customerService.getCustomers(theSortField);			
		}
		else {
			theCustomers = customerService.getCustomers(SortUtils.LAST_NAME);
		}
		
		model.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		Customer theCustomer=new Customer();
		model.addAttribute("customer", theCustomer);
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId,Model theModel) {
			
		Customer theCustomer=customerService.getCustomer(theId);
		
		theModel.addAttribute("customer",theCustomer);
		
		return "customer-form";
	}
	@GetMapping("/delete")
	public String update(@RequestParam("customerId") int theId) {
		customerService.delete(theId);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam("searchName") String name,Model theModel) {
		
		List<Customer> theCustomers=customerService.searchCustomer(name);
		
		theModel.addAttribute("customers", theCustomers);
		return "list-customers";
	}
}
