package com.technosoft.webservices.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.technosoft.webservices.validator.ValidPassword;

//@ApiModel(value = "Booking Details", description = "Contains all details of a booking")
@Entity
@Table(name = "tbl_user")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class Booking {
	
	private static final String VALIDATION="Username should have atleast 5 characters";
	//private static final String FORMULA="(" + "SELECT s.id " + "FROM PRICE s " + "WHERE s.STOCK_ID   = ID " + "ORDER BY s.timestamp DESC " + "LIMIT 1" + ")";

	@Id
	@GeneratedValue
	private Long id;
	private String firstname;
	private String lastname;
	@Size(min = 5, message = VALIDATION)
	@NotBlank(message="user name")
	@Column(unique = true)
	private String username;
	private Timestamp dob;
	private String email;
	@ValidPassword
	@NotEmpty
	private String password;
	
	
	

	public Booking() {
		super();
	}

	public Booking(Long id, String firstname, String lastname,
			@Size(min = 5, message = "Username should have atleast 5 characters") @NotBlank(message = "user name") String username,
			Timestamp dob, String email, String password) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.dob = dob;
		this.email = email;
		this.password = password;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the dob
	 */
	public Timestamp getDob() {
		return dob;
	}

	/**
	 * @param dob the dob to set
	 */
	public void setDob(Timestamp dob) {
		this.dob = dob;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the validation
	 */
	public static String getValidation() {
		return VALIDATION;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Stock [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", username=" + username
				+ ", dob=" + dob + ", email=" + email + ", password=" + password + "]";
	}

	

}