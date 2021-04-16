package com.example.creditcard.rest.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.creditcard.CreditcardApplication;
import com.example.creditcard.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * CreditCardRestControllerIntegrationTest
 * @author Diego Umana
 *
 */
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, 
		classes = CreditcardApplication.class)
@AutoConfigureMockMvc
class TransactionRestControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	private String json;
	private MvcResult result;
	private Transaction transaction;
	
	@Test
	void getTransactionsTest() throws Exception {

		result = mockMvc.perform(MockMvcRequestBuilders.get("/transaction/listTransactions/")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		
		assertThat(result.getResponse()).isNotNull();
		assertThat(result.getResponse().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);

	}
	
	@Test
	void getTransactionByNumberTest() throws Exception {

		result = mockMvc.perform(MockMvcRequestBuilders.get("/transaction/transactionNumber/0000001")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		objectMapper = new ObjectMapper();
		json = result.getResponse().getContentAsString();
		transaction = objectMapper.readValue(json, Transaction.class);	
		assertThat(transaction.getTransactionNumber()).isEqualTo("0000001");
	}
	

	@Test
	void getTransactionByCreditCardNumberTest() throws Exception{

		result =mockMvc.perform(MockMvcRequestBuilders.get("transaction/creditCardNumber/1111222233334444")
		.accept(MediaType.APPLICATION_JSON))
		.andReturn();
		objectMapper = new ObjectMapper();
		json = result.getResponse().getContentAsString();
		List<Transaction> transactions = Arrays.asList(objectMapper.readValue(json, Transaction[].class));
		assertThat(transactions).isNotEmpty();
	}
	
	

}
