package com.example.creditcard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.creditcard.model.CreditCard;
import com.example.creditcard.repository.CreditCardRepository;

@Service
public class CreditCardService implements AbstractService<CreditCard>{


	private final CreditCardRepository creditCardRepository;
	
	public CreditCardService(CreditCardRepository creditCardRepository) {
		this.creditCardRepository = creditCardRepository;
	}

	@Override
	public List<CreditCard> findAll() {
		return this.creditCardRepository.findAll();
	}

	@Override
	public Optional<CreditCard> findById(long id) {
		return this.creditCardRepository.findById(id);
	}

	@Override
	public Optional<CreditCard> findByNumber(String number) {
		return this.creditCardRepository.findByCreditCardNumber(number);
	}

	@Override
	public CreditCard save(CreditCard entity) {
		return this.creditCardRepository.save(entity);
	}
	
	
	
}
