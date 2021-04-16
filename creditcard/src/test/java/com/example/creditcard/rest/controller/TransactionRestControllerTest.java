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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.creditcard.model.Transaction;
import com.example.creditcard.service.TransactionService;

/**
 * TransactionRestControllerTest
 * @author Diego Umana
 *
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TransactionRestController.class)
class TransactionRestControllerTest {

	@Autowired
	private MockMvc mockMvc;
		
	@MockBean
	private TransactionService transactionService;

	@Test
	void getTransactionsTest() throws Exception {
		when(transactionService.findAll()).thenReturn(Collections.emptyList());

		mockMvc.perform(MockMvcRequestBuilders.get("/transaction/listTransactions").accept(MediaType.APPLICATION_JSON));

		verify(transactionService).findAll();
	}

	@Test
	void getTransactionByNumberTest() throws Exception{
		when(transactionService.findByNumber(Mockito.anyString())).thenReturn(Optional.of(mock(Transaction.class)));

		mockMvc.perform(MockMvcRequestBuilders.get("/transaction/transactionNumber/0000001").accept(MediaType.APPLICATION_JSON));

		verify(transactionService).findByNumber("0000001");
	}
	
	@Test
	void getTransactionByCreditCardNumberTest() throws Exception{
		when(transactionService.findByCreditCardNumber(Mockito.anyString())).thenReturn(Collections.emptyList());

		mockMvc.perform(MockMvcRequestBuilders.get("transaction/creditCardNumber/1111222233334444").accept(MediaType.APPLICATION_JSON));

		verify(transactionService).findByCreditCardNumber("1111222233334444");
	}
	

}
