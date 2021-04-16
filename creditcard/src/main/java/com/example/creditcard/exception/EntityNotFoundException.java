package com.example.creditcard.exception;

/**
 * Class to manage exception when not entity is found in db
 * and return a custom error message
 * @author Diego Umana
 *
 */
public class EntityNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * @param className
	 * @param value
	 */
	public EntityNotFoundException(String className, String value){
		super(String.format("Could not find %s %s",className,value));
	}
}
