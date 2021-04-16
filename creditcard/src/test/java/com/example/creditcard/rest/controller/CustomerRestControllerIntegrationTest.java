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
import com.example.creditcard.model.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * CustomerRestControllerIntegrationTest
 * @author Diego Umana
 *
 */
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, 
		classes = CreditcardApplication.class)
@AutoConfigureMockMvc
//@TestPropertySource
class CustomerRestControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	private String json;
	private MvcResult result;
	private Customer customer;
	
	@Test
	void getCustomersTest() throws Exception {

		result = mockMvc.perform(MockMvcRequestBuilders.get("/customer/listCustomers/")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		
		assertThat(result.getResponse()).isNotNull();
		assertThat(result.getResponse().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);

	}
	
	@Test
	void getCustomerByNumberTest() throws Exception {

		result = mockMvc.perform(MockMvcRequestBuilders.get("/customer/customerNumber/PL0000001/")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		objectMapper = new ObjectMapper();
		json = result.getResponse().getContentAsString();
		customer = objectMapper.readValue(json, Customer.class);	
		assertThat(customer.getName()).isEqualTo("PL0000001");
	}
	

	@Test
	void updateCustomerTest() throws Exception {
		String json = "{\r\n" + 
				"        \"name\": \"Diego\",\r\n" + 
				"        \"surname\": \"Umana\",\r\n" + 
				"        \"email\": \"diego@mail.com\",\r\n" + 
				"        \"address\": \"Mitre 123\",\r\n" + 
				"        \"postalCode\": \"33-300\",\r\n" + 
				"        \"telephoneNumber\": \"+48512345678\",\r\n" + 
				"        \"birthDate\": \"1981-11-21\"\r\n" + 
				"    }";
		
		result=mockMvc.perform(MockMvcRequestBuilders.put("/customer/update/PL0000001/")
				.content(json)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
				
		json = result.getResponse().getContentAsString();
		customer = objectMapper.readValue(json, Customer.class);	
		assertThat(customer.getAddress()).isEqualTo("Mitre 123");
	}
	

}
