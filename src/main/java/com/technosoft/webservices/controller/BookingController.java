package com.technosoft.webservices.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.technosoft.webservices.exception.BookingNotFoundException;
import com.technosoft.webservices.model.ApiResponse;
import com.technosoft.webservices.model.Booking;
import com.technosoft.webservices.model.BookingResponse;
import com.technosoft.webservices.service.BookingOperationsService;

/**
 * Controller method is used to get the rest resource for stock object
 *
 */
@RestController
public class BookingController {

	@Autowired
	private BookingOperationsService bookingOperationsService;

	/**
	 * @param Take book object in body
	 * @return response body with HTTP status
	 */

	@PostMapping("/api/customer")
	public ResponseEntity<ApiResponse> addStock(@Valid @RequestBody Booking book) {
		Booking booking = bookingOperationsService.addBook(book);
		ApiResponse apiResponse = new ApiResponse();
		if (booking != null) {
			apiResponse.setStatus("Added in queue");
			apiResponse.setMessage("Generated ID : " + booking.getId());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);

	}
	
	/**
	 * @param id Booking Id Based search
	 * @return Result fetched with specific ID
	 * @throws BookingNotFoundException Booking ID not present in the database
	 */
	@GetMapping("/api/customer/{id}")
	public ResponseEntity<BookingResponse> getBookById(@PathVariable long id) throws BookingNotFoundException {
		return ResponseEntity.ok(bookingOperationsService.findBook(id));
	}
}