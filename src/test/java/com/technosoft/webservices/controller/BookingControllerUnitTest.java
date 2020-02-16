package com.technosoft.webservices.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.technosoft.webservices.model.BookingResponse;
import com.technosoft.webservices.service.BookingOperationsServiceImpl;



@RunWith(SpringRunner.class)
public class BookingControllerUnitTest {

	private MockMvc mockMvc;

	@Mock
	private BookingOperationsServiceImpl bookingOperationsServiceImpl;

	@InjectMocks
	private BookingController bookingController;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();
	}

	@Test
	public void testBookListById() throws Exception {
		BookingResponse book = new BookingResponse(1001L,"diganta","mohapatra","digi",new Timestamp(0),"ananta.890@gmail.com","password");
		when(bookingOperationsServiceImpl.findBook(1001)).thenReturn(book);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/customer/1001").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.id", is(1001)))
				.andExpect(jsonPath("$.username", is("digi")));
	}

	@Test
	public void testInsertBooking() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/customer").contentType(MediaType.APPLICATION_JSON)
				.content("{\n" +
		                "    \"firstname\": \"Diganta\",\n" +
		                "    \"lastname\": \"Mohapatra\",\n" +
		                "    \"username\": \"digmohap2\",\n" +
		                "    \"password\": \"Licku.1991\"} ")
					.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated());

	}
}