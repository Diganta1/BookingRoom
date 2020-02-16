package com.technosoft.webservices.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.technosoft.webservices.exception.BookingNotFoundException;
import com.technosoft.webservices.model.Booking;
import com.technosoft.webservices.model.BookingResponse;
import com.technosoft.webservices.repository.BookingRepository;

@RunWith(MockitoJUnitRunner.class)
public class BookingOperationServiceImplTest {
	
	@InjectMocks
	private BookingOperationsServiceImpl testBookOperationServiceImpl;
	
	@Mock
	private BookingRepository bookRepository;

	@Test
	public void testFindBooking() {
		Booking book = new Booking(1001L,"diganta","mohapatra","digi",new Timestamp(0),"ananta.890@gmail.com","password");
		when(bookRepository.findById(1001L)).thenReturn(Optional.of(book));
		BookingResponse bookResponse = testBookOperationServiceImpl.findBook(1001L);
		assertEquals("Check  username", "digi", bookResponse.getUsername());
		assertEquals("Check Book ID", new Long(1001), new Long(bookResponse.getId()));	
		verify(bookRepository).findById(1001L);
		verifyNoMoreInteractions(bookRepository);
	}
	
	@Test(expected = BookingNotFoundException.class)
	public void testFindBookingNotFoundException() {
		testBookOperationServiceImpl.findBook(1001L);	
	}
	
	@Test
	public void testaddBookings() {
		Booking book = new Booking(1001L,"diganta","mohapatra","digi",new Timestamp(0),"ananta.890@gmail.com","password");
		when(bookRepository.save(book)).thenReturn((book));
		Booking bookingResponse = bookRepository.save(book);
		assertEquals("Check  username", "digi", bookingResponse.getUsername());
		assertEquals("Check booking ID", new Long(1001), new Long(bookingResponse.getId()));	
		assertEquals("check First Name", "diganta" , bookingResponse.getFirstname());
		verify(bookRepository).save(book);
		verifyNoMoreInteractions(bookRepository);
	}

	}
