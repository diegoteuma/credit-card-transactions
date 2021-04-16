package com.example.creditcard.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.creditcard.model.Transaction;

/**
 * TransactionRepository
 * @author Diego Umana
 *
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction>{

	/**
	 * Method to filter a Transaction by number
	 * @param transactionNumber
	 * @return a transaction filtered by transactionNumber
	 */
	public Optional<Transaction> findByTransactionNumber(String transactionNumber);
	/**
	 * Method to filter a Transaction given a creditCardNumber
	 * @param transactionNumber
	 * @return a transaction filtered by creditCardNumber
	 */
	public List<Transaction> findByCreditCardCreditCardNumber(String creditCardNumber);

}
