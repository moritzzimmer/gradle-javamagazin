package com.acme.am.dao;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;

import junit.framework.Assert;

import org.junit.Test;

import com.acme.am.dao.UserDao;
import com.acme.am.dao.UserDaoBean;
import com.acme.am.domain.User;

import de.akquinet.jbosscc.needle.annotation.ObjectUnderTest;
import de.akquinet.jbosscc.needle.db.transaction.VoidRunnable;

/**
 * @author <a href="mailto:moritz.zimmer@adesso.de">Moritz Zimmer</a>
 * @author <a href="mailto:marek.iwaszkiewicz@akquinet.de">Marek Iwaszkiewicz</a>
 */
public class UserDaoTest extends AbstractTest {

  @ObjectUnderTest
  private final UserDao userDao = new UserDaoBean();

  /**
   * Must fail because user reference is not set.
   * 
   * @throws Exception
   */
  @Test(expected = ConstraintViolationException.class)
  public void testFailSaveTask() throws Exception {
    
    final User user = new User(null, null, "vin", "disel", "triple@xXx.com");
    
    transactionHelper.executeInTransaction(new VoidRunnable() {
      @Override
      public void doRun(final EntityManager entityManager) throws Exception {
        userDao.persist(user);
      }
    });
  }

  @Test
  public void testSaveTask() throws Exception {

    // first create and save a user
    final User user = new User("xXx", "xXx", "vin", "disel", "triple@xXx.com");
    
    Assert.assertEquals(0, userDao.loadAll().size());
    
    transactionHelper.executeInTransaction(new VoidRunnable() {
      @Override
      public void doRun(final EntityManager entityManager) throws Exception {
        userDao.persist(user);
      }
    });
    
    Assert.assertEquals(1, userDao.loadAll().size());
  }

  @Test(expected = ConstraintViolationException.class)
  public void testFailOnWrongEMail() throws Exception {

    // first create and save a user
    final User user = new User("xXx", "xXx", "vin", "disel", "triple@");
    transactionHelper.executeInTransaction(new VoidRunnable() {
      @Override
      public void doRun(final EntityManager entityManager) throws Exception {
        transactionHelper.persist(user);
      }
    });

    Assert.fail("sould not have been reached!");
  }

}
