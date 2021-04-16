package com.example.creditcard.rest.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.creditcard.CreditcardApplication;
import com.example.creditcard.model.CreditCard;
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
class CreditCardRestControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	private String json;
	private MvcResult result;
	private CreditCard creditCard;
	
	@Test
	void getCreditCardsTest() throws Exception {

		result = mockMvc.perform(MockMvcRequestBuilders.get("/creditCard/listCreditCards/")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		
		assertThat(result.getResponse()).isNotNull();
		assertThat(result.getResponse().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);

	}
	
	@Test
	void getcreditCardByNumberTest() throws Exception {

		result = mockMvc.perform(MockMvcRequestBuilders.get("/creditCard/creditCardNumber/1111222233334444/")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		objectMapper = new ObjectMapper();
		json = result.getResponse().getContentAsString();
		creditCard = objectMapper.readValue(json, CreditCard.class);	
		assertThat(creditCard.getCreditCardNumber()).isEqualTo("1111222233334444");
	}
	

	@Test
	void createCreditCard() throws Exception {
		String json = "{\r\n" + 
				"        \"creditCardNumber\": \"4444333322221111\",\r\n" + 
				"        \"expirationDate\": \"2024-06-07\",\r\n" + 
				"        \"securityCode\": \"321\"\r\n" + 
				"    }";
		
		result=mockMvc.perform(MockMvcRequestBuilders.post("/creditCard/create")
				.content(json)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
				
		json = result.getResponse().getContentAsString();
		creditCard = objectMapper.readValue(json, CreditCard.class);	
		assertThat(creditCard.getCreditCardNumber()).isEqualTo("4444333322221111");
		assertThat(creditCard.getExpirationDate()).isEqualTo("2024-06-07");
		assertThat(creditCard.getSecurityCode()).isEqualTo("321");
	}
	

}
