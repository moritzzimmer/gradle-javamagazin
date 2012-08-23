package com.acme.am.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author <a href="mailto:moritz.zimmer@adesso.de">Moritz Zimmer</a>
 * @author <a href="mailto:marek.iwaszkiewicz@akquinet.de">Marek Iwaszkiewicz</a>
 */
@Entity
@NamedQueries({
	@NamedQuery(name = User.QUERY_LOAD_BY_USERNAME, query = "FROM User u WHERE u.login = :" + User.PARAM_USERNAME),
	@NamedQuery(name = User.QUERY_LOAD_BY_EMAIL, query = "FROM User u WHERE u.email = :" + User.PARAM_EMAIL)}
)
public class User extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	public static final String QUERY_LOAD_BY_USERNAME = "loadByUsername";
	public static final String PARAM_USERNAME = "login";

	public static final String QUERY_LOAD_BY_EMAIL = "loadByEmail";
	public static final String PARAM_EMAIL = "email";

	@NotNull
	@Column(unique = true)
	private String login;

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@NotNull
	@Pattern(regexp = ".+@.+\\.[a-z]+")
	@Column(unique = true)
	private String email;
	
	@NotNull
	private String password;

	// --------------- constrctor(s) ------------------------------------------------------

	public User() {
		// NOP
	}

	public User(String login, String password, String firstName, String lastName, String email) {
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	// ---------------- Getter / Setter ---------------------------------------------------

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

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

	public String getPassword() {
  	return password;
  }

	public void setPassword(String password) {
  	this.password = password;
  }

}
