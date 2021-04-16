package com.example.creditcard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.creditcard.model.Customer;

/**
 * CustomerRepository
 * @author Diego Umana
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	/**
	 * Method to filter Customers by number
	 * @param customerNumber
	 * @return a customer filtered by customerNumber
	 */
	public Optional<Customer> findByCustomerNumber(String customerNumber);
}
