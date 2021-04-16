package com.example.creditcard.service;

import java.util.List;
import java.util.Optional;

/**
 * Generic class to define Service behavior
 * @author Diego Umana
 *
 * @param <T>
 */
public interface AbstractService<T> {

	/**
	 * Method to store an Entity in db
	 * @param entity
	 * @return the Entity stored in db
	 */
	public T save(T entity);
	
	/**
	 * Method to list all Entities of a kind
	 * @return all entities of a kind
	 */
	public List<T> findAll();
	
	/**
	 * Method to find an Entity given an id
	 * @param id
	 * @return a specific Entity filtered by id
	 */
	public Optional<T> findById(long id);
	
	/**
	 * Method to find an Entity given a number
	 * @param number
	 * @return a specific Entity filtere by number
	 */
	public Optional<T> findByNumber(String number);
	
}
