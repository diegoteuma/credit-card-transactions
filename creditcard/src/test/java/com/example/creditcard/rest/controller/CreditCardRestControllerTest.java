package com.example.creditcard.rest.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.creditcard.model.CreditCard;
import com.example.creditcard.service.CreditCardService;

/**
 * CreditCardRestControllerTest
 * @author Diego Umana
 *
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CreditCardRestController.class)
@AutoConfigureMockMvc
class CreditCardRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CreditCardService creditCardService;

	@Test
	void getCreditCardsTest() throws Exception {
		when(creditCardService.findAll()).thenReturn(Collections.emptyList());

		mockMvc.perform(MockMvcRequestBuilders.get("/creditCard/listCreditCards").accept(MediaType.APPLICATION_JSON));

		verify(creditCardService).findAll();
	}

	@Test
	void getcreditCardByNumberTest() throws Exception{
		when(creditCardService.findByNumber(Mockito.anyString())).thenReturn(Optional.of(mock(CreditCard.class)));

		mockMvc.perform(MockMvcRequestBuilders.get("/creditCard/creditCardNumber/1111222233334444/").accept(MediaType.APPLICATION_JSON));

		verify(creditCardService).findByNumber("1111222233334444");
	}
	
	
	@Test
	void createCreditCardTest() throws Exception {
		when(creditCardService.save(Mockito.isA(CreditCard.class))).thenReturn(mock(CreditCard.class));

		mockMvc.perform(MockMvcRequestBuilders.get("/creditCard/create/").accept(MediaType.APPLICATION_JSON));

		verify(creditCardService).save(mock(CreditCard.class));
	}
	

}
