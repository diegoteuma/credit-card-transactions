package com.example.creditcard.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.creditcard.exception.EntityNotFoundException;
import com.example.creditcard.model.Customer;
import com.example.creditcard.service.CustomerService;

/**
 * RestController for Customers
 * @author Diego Umana
 *
 */
@RestController
@RequestMapping(path = "/customer")
public class CustomerRestController {

	private final CustomerService customerService;

	/**
	 * Constructor
	 * @param customerService
	 */
	public CustomerRestController(CustomerService customerService) {
		this.customerService = customerService;
	}

	/**
	 * Method to list all Customers
	 * @return all Customers stored in db
	 */
	@GetMapping(value = "/listCustomers")
	public List<Customer> getCustomers() {
		return customerService.findAll();

	}
	
	/**
	 * Method to filter Customers 
	 * @param customerNumber
	 * @return a Customer filtered by customerNumber
	 */
	@GetMapping("/customerNumber/{customerNumber}")
	public Customer getCustomerByNumber(@PathVariable("customerNumber") final String customerNumber) {
		return customerService.findByNumber(customerNumber)
				.orElseThrow(() -> new EntityNotFoundException(Customer.class.getSimpleName(),customerNumber));

	}

	/**
	 * Method to update a Customer
	 * @param newCustomer
	 * @param customerNumber
	 * @return the Customer Updated
	 */
	@PutMapping("/update/{customerNumber}")
	public Customer updateCustomer(@RequestBody Customer newCustomer, @PathVariable String customerNumber) {
		
		return customerService.findByNumber(customerNumber)
				.map(customerFound -> {
					
					customerFound.setName(newCustomer.getName());
					customerFound.setSurname(newCustomer.getSurname());
					customerFound.setEmail(newCustomer.getEmail());
					customerFound.setAddress(newCustomer.getAddress());
					customerFound.setPostalCode(newCustomer.getPostalCode());
					customerFound.setTelephoneNumber(newCustomer.getTelephoneNumber());
					customerFound.setBirthDate(newCustomer.getBirthDate());
					return customerService.save(customerFound);
				}).orElseThrow(() -> new EntityNotFoundException(Customer.class.getSimpleName(),customerNumber));
		
	}
}