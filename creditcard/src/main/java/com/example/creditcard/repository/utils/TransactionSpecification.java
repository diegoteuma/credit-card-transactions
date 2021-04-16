package com.example.creditcard.repository.utils;

import static org.springframework.data.jpa.domain.Specification.where;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.example.creditcard.model.Transaction;

/**
 * 
 * @author Diego Umana
 *
 */
@Component
public class TransactionSpecification extends BaseSpecification<Transaction, TransactionFilter>{

	@Override
	public Specification<Transaction> getFilter(TransactionFilter request) {
		return (root,query,cb) -> {
			return 
					where(transactionNumberContains(request.getTransactionNumber())
					.and(cardNumberContains(request.getCreditCardNumber()))
					.and(customerNumberContains(request.getCustomerNumber()))
							)
					.toPredicate(root,query,cb);
		};
	}

	/**
	 * Method to add filter by transactionNumber
	 * @param transactionNumber
	 * @return
	 */
	private Specification<Transaction> transactionNumberContains(String transactionNumber) {
		return(root,query,cb)->{
			if(transactionNumber == null || transactionNumber.isEmpty()) {
				return null;
			}
			return cb.like(
					cb.lower(root.get("transactionNumber")),
					containsLowerCase(transactionNumber)
					);
		};
	}

	/**
	 * Method to add filter by creditCardNumber
	 * @param creditCardNumber
	 * @return
	 */
	private Specification<Transaction> cardNumberContains(String creditCardNumber) {
		return(root,query,cb)->{
			if(creditCardNumber == null || creditCardNumber.isEmpty()) {
				return null;
			}
			return cb.like(
					cb.lower(root.join("creditCard").get("creditCardNumber")),
					containsLowerCase(creditCardNumber)
					);
		};
	}

	/**
	 * Method to add filter by customerNumber
	 * @param customerNumber
	 * @return
	 */
	private Specification<Transaction> customerNumberContains(String customerNumber) {
		return(root,query,cb)->{
			if(customerNumber == null || customerNumber.isEmpty()) {
				return null;
			}
			return cb.like(
					cb.lower(root.join("creditCard").join("customer").get("customerNumber")),
					containsLowerCase(customerNumber)
					);
		};
	}

	
}
