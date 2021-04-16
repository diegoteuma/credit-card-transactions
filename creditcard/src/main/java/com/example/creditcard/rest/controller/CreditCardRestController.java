package com.example.creditcard.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.creditcard.exception.EntityNotFoundException;
import com.example.creditcard.model.CreditCard;
import com.example.creditcard.service.CreditCardService;

/**
 * RestController for Credit Cards
 * @author Diego Umana
 *
 */
@RestController
@RequestMapping(path = "/creditCard")
public class CreditCardRestController {
	
	private final CreditCardService creditCardService;

	/**
	 * Constructor
	 * @param creditCardService
	 */
	public CreditCardRestController(CreditCardService creditCardService) {
		this.creditCardService = creditCardService;
	}

	/**
	 * Method to get all Credit Cards
	 * @return all the Credit Cards stored in db
	 */
	@GetMapping(value = "/listCreditCards")
	public List<CreditCard> getCreditCards() {
		return creditCardService.findAll();

	}

	/**
	 * Method to filter Credit Cards by number
	 * @param creditCardNumber
	 * @return a Credit Card filtered by creditCardNumber
	 */
	@GetMapping("/creditCardNumber/{creditCardNumber}")
	public CreditCard getcreditCardByNumber(@PathVariable("creditCardNumber") final String creditCardNumber) {
		return creditCardService.findByNumber(creditCardNumber)
				.orElseThrow(() -> new EntityNotFoundException(CreditCard.class.getSimpleName(),creditCardNumber));

	}

	/**
	 * Method to create a Credit Card
	 * @param newCreditCard
	 * @return a new Credit Card
	 */
	@PostMapping("/create")
	public CreditCard createCreditCard(@RequestBody CreditCard newCreditCard) {
		return creditCardService.save(newCreditCard);
		
	}

}