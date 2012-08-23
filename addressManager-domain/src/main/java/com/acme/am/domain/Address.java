package com.acme.am.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * @author <a href="mailto:moritz.zimmer@adesso.de">Moritz Zimmer</a>
 * @author <a href="mailto:marek.iwaszkiewicz@akquinet.de">Marek Iwaszkiewicz</a>
 */
@Embeddable
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	private String street;

	private String number;

	private String city;

	private String country;

	private String zipCode;

	// --------------- constrctor(s) ------------------------------------------------------

	public Address() {
		// NOP
	}

	public Address(String street, String number, String city, String country, String zipCode) {
		super();
		this.street = street;
		this.number = number;
		this.city = city;
		this.country = country;
		this.zipCode = zipCode;
	}

	// ---------------- Getter / Setter ---------------------------------------------------

	public String getStreet() {
  	return street;
  }

	public void setStreet(String street) {
  	this.street = street;
  }

	public String getNumber() {
  	return number;
  }

	public void setNumber(String number) {
  	this.number = number;
  }

	public String getCity() {
  	return city;
  }

	public void setCity(String city) {
  	this.city = city;
  }

	public String getCountry() {
  	return country;
  }

	public void setCountry(String country) {
  	this.country = country;
  }

	public String getZipCode() {
  	return zipCode;
  }

	public void setZipCode(String zipCode) {
  	this.zipCode = zipCode;
  }

}
