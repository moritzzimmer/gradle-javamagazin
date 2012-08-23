package com.acme.am.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author <a href="mailto:moritz.zimmer@adesso.de">Moritz Zimmer</a>
 * @author <a href="mailto:marek.iwaszkiewicz@akquinet.de">Marek Iwaszkiewicz</a>
 */
@Entity
@NamedQuery(name = Contact.QUERY_LOAD_FOR_USER, query = "FROM Contact c WHERE c.user = :" + Contact.PARAM_USER)
public class Contact extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	// query: load tasks by category
	public static final String QUERY_LOAD_FOR_USER = "loadForUser";
	public static final String PARAM_USER = "user";

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@NotNull
	@Pattern(regexp = ".+@.+\\.[a-z]+")
	private String email;

	@Embedded
	private Address address;

	@ManyToOne(optional = false)
	@JoinColumn(name = "USER_ID", nullable = false, updatable = false)
	private User user;

	// --------------- constrctor(s) ------------------------------------------------------

	public Contact() {
		// NOP
	}

	public Contact(String firstName, String lastName, String email, Address address, User user) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.user = user;
	}

	// ---------------- Getter / Setter ---------------------------------------------------

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		if (address == null) {
			address = new Address();
		}
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
