package com.example.creditcard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.creditcard.model.Customer;
import com.example.creditcard.repository.CustomerRepository;

@Service
public class CustomerService implements AbstractService<Customer>{

	private final CustomerRepository customerRepository;
	
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public List<Customer> findAll() {
		return this.customerRepository.findAll();
	}

	@Override
	public Optional<Customer> findById(long id) {
		return this.customerRepository.findById(id);
	}

	@Override
	public Optional<Customer> findByNumber(String number) {
		return this.customerRepository.findByCustomerNumber(number);
	}

	@Override
	public Customer save(Customer entity) {
		return this.customerRepository.save(entity);
	}
	
	
	
}
