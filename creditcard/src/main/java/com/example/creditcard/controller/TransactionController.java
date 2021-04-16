package com.example.creditcard.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.creditcard.model.Transaction;
import com.example.creditcard.repository.utils.TransactionFilter;
import com.example.creditcard.service.TransactionService;

/**
 * TransactionController to work with MVC
 * @author Diego Umana
 *
 */
@Named
@ViewScoped
public class TransactionController {

	private final TransactionService transactionService;
	private Transaction selectedTransaction;
	private LazyDataModel<Transaction> lazyDataModel;
	
	@Autowired
	private TransactionFilter transactionFilter;
	
	/**
	 * Constructor
	 * @param transactionService
	 */
	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	
	/**
	 * method to find all transactions given filter criteria, works in lazy model way
	 * using PageAndSorting, Specificaction and LazyDataModel from Primefaces
	 */
	public void findAll() {
		this.setLazyDataModel(this.transactionService.getLazyTransactionModel(this.getTransactionFilter()));
	}

	/**
	 * Method to clean all atributes from the search criteria
	 */
	public void cleanSearchCriteria() {
		transactionFilter.setCreditCardNumber(null);
		transactionFilter.setCustomerNumber(null);
		transactionFilter.setTransactionNumber(null);
	}
	/**
	 * @return the selectedTransaction
	 */
	public Transaction getSelectedTransaction() {
		return selectedTransaction;
	}

	/**
	 * @param selectedTransaction the selectedTransaction to set
	 */
	public void setSelectedTransaction(Transaction selectedTransaction) {
		this.selectedTransaction = selectedTransaction;
	}

	/**
	 * @return the lazyDataModel
	 */
	public LazyDataModel<Transaction> getLazyDataModel() {
		return lazyDataModel;
	}

	/**
	 * @param lazyDataModel the lazyDataModel to set
	 */
	public void setLazyDataModel(LazyDataModel<Transaction> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	/**
	 * @return the transactionFilter
	 */
	public TransactionFilter getTransactionFilter() {
		return transactionFilter;
	}

	/**
	 * @param transactionFilter the transactionFilter to set
	 */
	public void setTransactionFilter(TransactionFilter transactionFilter) {
		this.transactionFilter = transactionFilter;
	}
}
