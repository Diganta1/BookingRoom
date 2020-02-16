package com.technosoft.webservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.technosoft.webservices.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
	Booking findByUsername(String username);
}