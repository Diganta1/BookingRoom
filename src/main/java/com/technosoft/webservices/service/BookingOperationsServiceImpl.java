/**
 * 
 */
package com.technosoft.webservices.service;

import java.util.Arrays;
import java.util.List;

import javax.persistence.RollbackException;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.technosoft.webservices.exception.BookingNotFoundException;
import com.technosoft.webservices.model.Booking;
import com.technosoft.webservices.model.BookingResponse;
import com.technosoft.webservices.repository.BookingRepository;

/**
 * Implementation class for Stock operation service
 * 
 * @author diganta
 *
 */
@Service(value = "userService")
public class BookingOperationsServiceImpl implements BookingOperationsService, UserDetailsService {

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(BookingOperationsServiceImpl.class);
	private static final String INFO_ADD = "Saved Item id:{} and username :{} into db";
	private static final String INVALID_CREDENTIALS = "Invalid credential.";
	private static final String ROLE_ADMIN = "ROLE_ADMIN";

	@Autowired
	private BookingRepository bookingRepository;

	/**
	 * register new book
	 */
	@Override
	public Booking addBook(Booking book) throws RollbackException {
		Booking bookdetails = new Booking();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		bookdetails.setId(book.getId());
		bookdetails.setFirstname(book.getFirstname());
		bookdetails.setLastname(book.getLastname());
		bookdetails.setUsername(book.getUsername());
		bookdetails.setDob(book.getDob());
		bookdetails.setEmail(book.getEmail());
		bookdetails.setPassword(encoder.encode(book.getPassword()));
		LOGGER.info(INFO_ADD, bookdetails.getId(), bookdetails.getUsername());
		return bookingRepository.save(bookdetails);
	}

	@Override
	public BookingResponse findBook(long bookingID) {

		Booking book = bookingRepository.findById(bookingID).orElseThrow(
				() -> new BookingNotFoundException(String.format("Booking %s does not exists", bookingID)));
		return new BookingResponse(book.getId(), book.getFirstname(), book.getLastname(), book.getUsername(),
				book.getDob(), book.getEmail(), "*****");
	}

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		Booking user = bookingRepository.findByUsername(userId);
		if (user == null) {
			throw new UsernameNotFoundException(INVALID_CREDENTIALS);
		}
		return new org.springframework.security.core.userdetails.User(String.valueOf(user.getId()), user.getPassword(),
				getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority(ROLE_ADMIN));
	}
}