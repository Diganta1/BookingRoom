package com.technosoft.webservices.service;

import com.technosoft.webservices.model.Booking;
import com.technosoft.webservices.model.BookingResponse;

/**
 *  Interface for service call
 * @author diganta
 *
 */
public interface BookingOperationsService {

	public Booking addBook(Booking book);

	public BookingResponse findBook(long bookingId);

	
}
