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

import com.example.creditcard.model.Customer;
import com.example.creditcard.service.CustomerService;

/**
 * CustomerRestControllerTest
 * @author Diego Umana
 *
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CustomerRestController.class)
class CustomerRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService customerService;

	@Test
	void getCustomersTest() throws Exception {
		when(customerService.findAll()).thenReturn(Collections.emptyList());

		mockMvc.perform(MockMvcRequestBuilders.get("/customer/listCustomers/").accept(MediaType.APPLICATION_JSON));

		verify(customerService).findAll();
	}

	@Test
	void getCustomerByNameTest() throws Exception{
		when(customerService.findByNumber(Mockito.anyString())).thenReturn(Optional.of(mock(Customer.class)));

		mockMvc.perform(MockMvcRequestBuilders.get("/customer/customerNumber/PL0000001/").accept(MediaType.APPLICATION_JSON));

		verify(customerService).findByNumber("PL0000001");
	}
	
	
	@Test
	void updateCustomerTest() throws Exception {
		when(customerService.save(Mockito.isA(Customer.class))).thenReturn(mock(Customer.class));

		mockMvc.perform(MockMvcRequestBuilders.get("/customer/update/PL0000001/").accept(MediaType.APPLICATION_JSON));

		verify(customerService).save(mock(Customer.class));
	}
	

}
