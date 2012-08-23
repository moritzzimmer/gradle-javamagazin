package com.acme.am.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

import org.junit.Assert;
import org.junit.Test;

import com.acme.am.domain.Address;
import com.acme.am.domain.Contact;
import com.acme.am.domain.User;

import de.akquinet.jbosscc.needle.annotation.ObjectUnderTest;
import de.akquinet.jbosscc.needle.db.transaction.VoidRunnable;

/**
 * @author <a href="mailto:moritz.zimmer@adesso.de">Moritz Zimmer</a>
 * @author <a href="mailto:marek.iwaszkiewicz@akquinet.de">Marek Iwaszkiewicz</a>
 */
public class ContactDaoTest extends AbstractTest {

	@ObjectUnderTest
	private final ContactDao contactDao = new ContactDaoBean();

	/**
	 * Must fail because user reference is not set.
	 * 
	 * @throws Exception
	 */
	@Test(expected = PersistenceException.class)
	public void testFailSaveTask() throws Exception {
		transactionHelper.executeInTransaction(new VoidRunnable() {
			@Override
			public void doRun(final EntityManager entityManager) throws Exception {
				final Contact task = new Contact("hans", "wurst", "hans@wurst.de", new Address(), null);
				contactDao.persist(task);
			}
		});
	}

	/**
	 * Must fail because user reference is not set.
	 * 
	 * @throws Exception
	 */
	@Test(expected = ConstraintViolationException.class)
	public void testFailOnInvalidEMail() throws Exception {
		
		final User user = new User("xXx", "xXx", "vin", "disel", "triple@xXx.com");
		transactionHelper.executeInTransaction(new VoidRunnable() {
			@Override
			public void doRun(final EntityManager entityManager) throws Exception {
				transactionHelper.persist(user);
			}
		});
		
		transactionHelper.executeInTransaction(new VoidRunnable() {
			@Override
			public void doRun(final EntityManager entityManager) throws Exception {
				final Contact task = new Contact("hans", "wurst", "hans@", new Address(), user);
				contactDao.persist(task);
			}
		});
	}

	@Test
	public void testSaveTask() throws Exception {

		// first create and save a user
		final User user = new User("xXx", "xXx", "vin", "disel", "triple@xXx.com");
		transactionHelper.executeInTransaction(new VoidRunnable() {
			@Override
			public void doRun(final EntityManager entityManager) throws Exception {
				transactionHelper.persist(user);
			}
		});

		// we expect the database to be empty
		Assert.assertEquals(0, contactDao.loadAll().size());
		// write access must be executed within a transaction
		transactionHelper.executeInTransaction(new VoidRunnable() {
			@Override
			public void doRun(final EntityManager entityManager) throws Exception {
				final Contact task = new Contact("hans", "wurst", "hans@wurst.de", new Address(), user);
				contactDao.persist(task);
			}
		});

		Assert.assertEquals(1, contactDao.loadAll().size());
	}

	@Test
	public void testFindForUser() throws Exception {

		// first create and save a user
		final User user1 = new User("ironman", "ironman", "tony", "stark", "tony.stark@starkindustries.com");
		final User user2 = new User("spiderman", "spiderman", "peter", "parker", "peter.parker@marvel.com");
		transactionHelper.executeInTransaction(new VoidRunnable() {
			@Override
			public void doRun(final EntityManager entityManager) throws Exception {
				transactionHelper.persist(user1);
				transactionHelper.persist(user2);
			}
		});

		// we expect the database to be empty
		Assert.assertEquals(0, contactDao.loadAll().size());
		// write access must be executed within a transaction
		transactionHelper.executeInTransaction(new VoidRunnable() {
			@Override
			public void doRun(final EntityManager entityManager) throws Exception {
				final Contact contanct1 = new Contact("a", "a", "a@a.a", null, user1);
				final Contact contanct2 = new Contact("b", "b", "b@b.b", new Address(), user1);
				final Contact contanct3 = new Contact("c", "c", "c@c.c", new Address(), user2);
				contactDao.persist(contanct1);
				contactDao.persist(contanct2);
				contactDao.persist(contanct3);
			}
		});

		Assert.assertEquals(3, contactDao.loadAll().size());

		Assert.assertEquals(2, contactDao.findForUser(user1).size());
		Assert.assertEquals(1, contactDao.findForUser(user2).size());
	}
}
