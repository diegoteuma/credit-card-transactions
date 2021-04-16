package com.example.creditcard.repository.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.example.creditcard.model.Transaction;
import com.example.creditcard.repository.TransactionRepository;

/**
 * Class to manage data volumens, sorting and filter in lazy way
 * @author Diego Umana
 *
 */
public class LazyTransactionModel extends LazyDataModel<Transaction> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TransactionRepository transactionRepository;
	private TransactionSpecification transactionSpecification;
	private TransactionFilter transactionFilter;
	private List<Transaction> transactions = new ArrayList<>();

	/**
	 * Constructor
	 * @param transactionRepository
	 * @param transactionSpecification
	 * @param transactionFilter
	 */
	public LazyTransactionModel(TransactionRepository transactionRepository,
			TransactionSpecification transactionSpecification, TransactionFilter transactionFilter) {
		this.transactionRepository = transactionRepository;
		this.transactionSpecification = transactionSpecification;
		this.transactionFilter = transactionFilter;
	}

	@Override
	public List<Transaction> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
		Pageable pagin = PageRequest.of((first / pageSize), pageSize, Sort.by(createOrder(sortBy)));
		Page<Transaction> page = transactionRepository.findAll(transactionSpecification.getFilter(transactionFilter), pagin);
		transactions = page.getContent();
		setRowCount((int) page.getTotalElements());
		return page.getContent();
	}

	@Override
	public Transaction getRowData(String rowKey) {
		return transactions.stream().filter(t -> t.getId() == Integer.parseInt(rowKey)).findFirst().orElse(null);
	}

	@Override
	public String getRowKey(Transaction transaction) {
		return String.valueOf(transaction.getId());
	}

	/**
	 * Method to create the Order criteria in searching
	 * @param sortBy
	 * @return
	 */
	private List<Order> createOrder(Map<String, SortMeta> sortBy) {
		List<Order> orders = new ArrayList<>();
		sortBy.entrySet().stream().forEach(entry->{
			Direction direction = entry.getValue().getOrder().isAscending() ? Sort.Direction.ASC : Sort.Direction.DESC;
			orders.add(new Order(direction,entry.getKey()));
		});
		return orders;
	}

}
