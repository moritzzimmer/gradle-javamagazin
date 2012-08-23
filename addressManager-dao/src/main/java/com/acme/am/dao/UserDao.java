package com.acme.am.dao;

import javax.ejb.Local;

import com.acme.am.dao.common.GenericDao;
import com.acme.am.domain.User;

/**
 * @author <a href="mailto:marek.iwaszkiewicz@akquinet.de">Marek Iwaszkiewicz</a>
 * @author <a href="mailto:moritz.zimmer@adesso.de">Moritz Zimmer</a>
 */
@Local
public interface UserDao extends GenericDao<User> {

  User loadByUsername(final String username);

}
