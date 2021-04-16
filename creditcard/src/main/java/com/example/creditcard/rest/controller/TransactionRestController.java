package com.example.creditcard.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.creditcard.exception.EntityNotFoundException;
import com.example.creditcard.model.Transaction;
import com.example.creditcard.service.TransactionService;

/**
 * RestController for Transactions
 * @author Diego Umana
 *
 */
@RestController
@RequestMapping(path = "/transaction")
public class TransactionRestController {

	private final TransactionService transactionService;

	/**
	 * Constructor
	 * @param transactionService
	 */
	public TransactionRestController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	
	/**
	 * Method to get all Transactions
	 * @return all the Transactions stored in db
	 */
	@GetMapping(value = "/listTransactions")
	public List<Transaction> getTransations() {
		return this.transactionService.findAll();

	}
	
	/**
	 * Method to filter Transactions by transactionNumber
	 * @param transactionNumber
	 * @return a transaction filtered by transactionNumber
	 */
	@GetMapping("/transactionNumber/{transactionNumber}")
	public Transaction getTransactionByNumber(@PathVariable("transactionNumber") final String transactionNumber) {
		return transactionService.findByNumber(transactionNumber)
				.orElseThrow(() -> new EntityNotFoundException(Transaction.class.getSimpleName(),transactionNumber));

	}
	
	/**
	 * Method to filter Transactions by creditCardNumber
	 * @param creditCardNumber
	 * @return a Transaction filtered by creditCardNumber
	 */
	@GetMapping("/creditCardNumber/{creditCardNumber}")
	public List<Transaction> getTransactionByCreditCardNumber(@PathVariable("creditCardNumber") final String creditCardNumber) {
		return transactionService.findByCreditCardNumber(creditCardNumber);

	}

	
}