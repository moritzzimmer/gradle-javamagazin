package com.acme.am.business;

import java.util.List;

import javax.ejb.Local;

import com.acme.am.domain.User;

/**
 * @author <a href="mailto:moritz.zimmer@adesso.de">Moritz Zimmer</a>
 * @author <a href="mailto:marek.iwaszkiewicz@akquinet.de">Marek Iwaszkiewicz</a>
 */
@Local
public interface UserService {

  User loadById(final Long userId);
  
  User loadByUsername(final String username);
  
  void saveUser(final User user);

  void mergeUser(final User user);

  void removeUser(final User user);

  List<User> loadAll();

}
