package com.acme.am.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.jboss.solder.logging.Logger;

import com.acme.am.dao.ContactDao;
import com.acme.am.dao.UserDao;
import com.acme.am.domain.Contact;
import com.acme.am.domain.User;

/**
 * @author <a href="mailto:moritz.zimmer@adesso.de">Moritz Zimmer</a>
 * @author <a href="mailto:marek.iwaszkiewicz@akquinet.de">Marek Iwaszkiewicz</a>
 */
@Stateless
public class UserServiceBean implements UserService {

  @Inject
  private Logger log;

  @EJB
  private UserDao userDao;

  @EJB
  private ContactDao taskDao;

  // -------------- interface method implementations ------------------------------------

  @Override
  public User loadById(final Long userId) {
    return userDao.load(userId);
  }

  @Override
  public User loadByUsername(final String username) {
    return userDao.loadByUsername(username);
  }

  @Override
  public void saveUser(User user) {
    userDao.persist(user);
  }

  @Override
  public void mergeUser(User user) {
    userDao.persist(user);
  }

  @Override
  public void removeUser(User user) {
    log.debugv("removing user {0}", user.getLogin());

    List<Contact> tasksToRemove = taskDao.findForUser(user);

    if (!tasksToRemove.isEmpty()) {
      log.debugv("user {0} has {1} tasks which have to be deleted as well", user.getLogin(), tasksToRemove.size());

      for (Contact task : tasksToRemove) {
        taskDao.delete(task);
      }
    }

    userDao.delete(loadById(user.getId()));
  }

  @Override
  public List<User> loadAll() {
    return userDao.loadAll();
  }

}
