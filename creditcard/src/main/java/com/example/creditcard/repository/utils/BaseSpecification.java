package com.example.creditcard.repository.utils;

import org.springframework.data.jpa.domain.Specification;

/**
 * Generic Class for use Spring Specification
 * @author Diego Umana
 *
 * @param <T>
 * @param <U>
 */
public abstract class BaseSpecification<T,U> {
	
	private static final String WILDCARD = "%";
	
	/**
	 * Method to construct the filter criteria
	 * @param request
	 * @return the Specification 
	 */
	public abstract Specification<T> getFilter(U request);
	
	/**
	 * Method to add Wildcard and convert to lowercase to can perform queries with like
	 * @param searchField
	 * @return
	 */
	protected String containsLowerCase(String searchField) {
		return WILDCARD + searchField.toLowerCase() + WILDCARD;
	}

}
