package com.acme.am.bootstrap;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.jboss.solder.logging.Logger;

import com.acme.am.dao.ContactDao;
import com.acme.am.dao.UserDao;
import com.acme.am.dao.common.GenericDao;
import com.acme.am.domain.AbstractEntity;
import com.acme.am.domain.Address;
import com.acme.am.domain.Contact;
import com.acme.am.domain.User;

/**
 * @author <a href="mailto:moritz.zimmer@adesso.de">Moritz Zimmer</a>
 * @author <a href="mailto:marek.iwaszkiewicz@akquinet.de">Marek Iwaszkiewicz</a>
 */
@Singleton
@Startup
public class BootstrapBean {

	@Inject
	private Logger log;

	// @Inject geht hier nicht wegen der Vererbung (GenericDao<E,ID>)!!! ->
	// Weld/CDI-Bug
	@EJB
	private ContactDao aufgabeDao;

	@EJB
	private UserDao userDao;

	@PostConstruct
	public void insert() {
		log.debug("Setup data in data base...");

		setupData();

		log.debug("Test data has been successfully set up");
	}

	private void setupData() {

		// create three users
		final User user1 = new User("xXx", "xXx", "vin", "disel", "triple@xXx.com");
		final User user2 = new User("ironman", "ironman", "tony", "stark", "tony.stark@starkindustries.com");
		final User user3 = new User("spiderman", "spiderman", "peter", "parker", "peter.parker@marvel.com");

		// create addresses
		final Address address1 = new Address("Berliner Allee", "12", "Berlin", "D", "12345");
		final Address address2 = new Address("ul.Srebrna", "6", "Pless", "PL", "25452");
		final Address address3 = new Address("Bruchsteinweg", "22", "Hamburg", "D", "22222");
		final Address address4 = new Address("Park Avenue", "111", "New York", "USA", "5643");
		final Address address5 = new Address("Roestiweg", "3c", "Bern", "CH", "5223");
		final Address address6 = new Address("Strasse Nr. 3", "3", "Berlin", "D", "12222");
		final Address address7 = new Address("Yorkstr", "7", "Berlin", "D", "12121");
		
		// add contacts for each user
		final Contact c1 = new Contact("Hans", "Wurst", "hans.wurst@gmx.de", address1, user1);
		final Contact c2 = new Contact("Hugo", "Boss", "hb@style.com", address2, user1);

		final Contact c3 = new Contact("Mary Jane", "Watson", "mj@yahoo.com", address3, user2);
		final Contact c4 = new Contact("Harry", "Osborn", "harry@osborn.com", address4, user2);

		final Contact c5 = new Contact("Ho", "Yinsen", "ho@ho.ho", address5, user3);
		final Contact c6 = new Contact("Peter", "Parker", "p.p@hotmail.com", address6, user3);
		final Contact c7 = new Contact("Bruce", "Banner", "banner@labs.com", address7, user3);

		save(userDao, user1, user2, user3);
		save(aufgabeDao, c1, c2, c3, c4, c5, c6, c7);
	}

	private <T extends AbstractEntity> void save(final GenericDao<T> dao, final T... entities) {
		for (final T entity : entities) {
			dao.persist(entity);
		}
	}
}
