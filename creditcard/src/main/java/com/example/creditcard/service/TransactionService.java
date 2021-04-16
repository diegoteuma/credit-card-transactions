package com.example.creditcard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.creditcard.model.Transaction;
import com.example.creditcard.repository.TransactionRepository;
import com.example.creditcard.repository.utils.LazyTransactionModel;
import com.example.creditcard.repository.utils.TransactionFilter;
import com.example.creditcard.repository.utils.TransactionSpecification;

@Service
public class TransactionService implements AbstractService<Transaction> {

	private final TransactionRepository transactionRepository;
	
	//for web purpose only
	@Autowired
	private TransactionSpecification transactionSpecification;
	private LazyTransactionModel lazyTransactionModel;

	public TransactionService(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	@Override
	public Transaction save(Transaction entity) {
		return this.transactionRepository.save(entity);
	}

	@Override
	public List<Transaction> findAll() {
		return this.transactionRepository.findAll();
	}

	@Override
	public Optional<Transaction> findById(long id) {
		return this.transactionRepository.findById(id);
	}

	@Override
	public Optional<Transaction> findByNumber(String number) {
		return this.transactionRepository.findByTransactionNumber(number);
	}

	/**
	 * Method to filter transactions by creditCardNumber
	 * @param creditCardNumber
	 * @return a list of transactions filterd by creditCardNumber
	 */
	@Cacheable(value="transactionsByCreditCardNumber")
	public List<Transaction> findByCreditCardNumber(String creditCardNumber) {
		return this.transactionRepository.findByCreditCardCreditCardNumber(creditCardNumber);
	}
	
	/**
	 * Method to get or create a Lazy model
	 * @param transactionFilter
	 * @return a LazyDataModel for Transactions
	 */
	public LazyTransactionModel getLazyTransactionModel(TransactionFilter transactionFilter ) {
		if(lazyTransactionModel !=null) {
			return lazyTransactionModel;
		} else {
			return new LazyTransactionModel(transactionRepository,transactionSpecification, transactionFilter);
		}
	}

}
