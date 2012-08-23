package com.acme.am.business.ut;

import javax.persistence.EntityManager;

import junit.framework.Assert;

import org.junit.Test;

import com.acme.am.business.UserService;
import com.acme.am.business.UserServiceBean;
import com.acme.am.dao.AbstractTest;
import com.acme.am.dao.ContactDao;
import com.acme.am.dao.ContactDaoBean;
import com.acme.am.dao.UserDao;
import com.acme.am.dao.UserDaoBean;
import com.acme.am.domain.Contact;
import com.acme.am.domain.User;

import de.akquinet.jbosscc.needle.annotation.InjectInto;
import de.akquinet.jbosscc.needle.annotation.ObjectUnderTest;
import de.akquinet.jbosscc.needle.db.transaction.VoidRunnable;

/**
 * @author <a href="mailto:moritz.zimmer@adesso.de">Moritz Zimmer</a>
 * @author <a href="mailto:marek.iwaszkiewicz@akquinet.de">Marek Iwaszkiewicz</a>
 */
public class UserServiceDatabaseTest extends AbstractTest {

  @SuppressWarnings("unused")
  @ObjectUnderTest
  @InjectInto(targetComponentId = "userService")
  private final UserDao userDao = new UserDaoBean();

  @ObjectUnderTest
  private final UserService userService = new UserServiceBean();

  @ObjectUnderTest
  @InjectInto(targetComponentId = "userService")
  private final ContactDao taskDao = new ContactDaoBean();
 
  // -------------- test methods --------------------------------------------------------
  
  @Test
  public void testUserService() throws Exception {
    
    final User user = new User("xXx", "xXx", "vin", "disel", "triple@xXx.com");
    
    Assert.assertEquals(0, userService.loadAll().size());
    
    transactionHelper.executeInTransaction(new VoidRunnable() {
      @Override
      public void doRun(final EntityManager entityManager) throws Exception {
        userService.saveUser(user);
      }
    });
    
    Assert.assertEquals(1, userService.loadAll().size());
  }

  @Test
  public void testRemoveUser() throws Exception {

    Assert.assertEquals(0, userService.loadAll().size());
    
    // first create and save a user
    final User user1 = new User("xXx", "xXx", "vin", "disel", "triple@xXx.com");
    final User user2 = new User("spiderman", "spiderman", "peter", "parker", "peter.parker@marvel.com");
    
    transactionHelper.executeInTransaction(new VoidRunnable() {
      @Override
      public void doRun(final EntityManager entityManager) throws Exception {
        userService.saveUser(user1);
        userService.saveUser(user2);
      }
    });
    
		final Contact u1_c1 = new Contact("a1", "a1", "a1@a1.x", null, user1);
    final Contact u1_c2 = new Contact("a2", "a2", "a2@a2.x", null, user1);
    final Contact u1_c3 = new Contact("a3", "a3", "a3@a3.x", null, user1);

    final Contact u2_c1 = new Contact("b1", "b1", "b1@b1.x", null, user2);
    final Contact u2_c2 = new Contact("b2", "b2", "b2@b2.x", null, user2);
    
    transactionHelper.executeInTransaction(new VoidRunnable() {
      @Override
      public void doRun(final EntityManager entityManager) throws Exception {
        transactionHelper.persist(u1_c1);
        transactionHelper.persist(u1_c2);
        transactionHelper.persist(u1_c3);

        transactionHelper.persist(u2_c1);
        transactionHelper.persist(u2_c2);
      }
    });

    Assert.assertEquals(2, userService.loadAll().size());
    Assert.assertEquals(5, taskDao.loadAll().size());
    
    transactionHelper.executeInTransaction(new VoidRunnable() {
      @Override
      public void doRun(final EntityManager entityManager) throws Exception {
        userService.removeUser(user1);
      }
    });
    
    Assert.assertEquals(1, userService.loadAll().size());
    Assert.assertEquals(2, taskDao.loadAll().size());
    Assert.assertNull(taskDao.load(u1_c1.getId()));
    Assert.assertNotNull(taskDao.load(u2_c1.getId()));
    Assert.assertNotNull(taskDao.load(u2_c2.getId()));

  }

}
