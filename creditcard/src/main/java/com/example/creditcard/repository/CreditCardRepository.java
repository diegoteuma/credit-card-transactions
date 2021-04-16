package com.example.creditcard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.creditcard.model.CreditCard;

/**
 * CreditCardRepository
 * @author Diego Umana
 *
 */
@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

	/**
	 * Method to filter CreditCards by number
	 * @param creditCardNumber
	 * @return a creditCard filtered by creditCardNumber
	 */
	public Optional<CreditCard> findByCreditCardNumber(String creditCardNumber);


}
