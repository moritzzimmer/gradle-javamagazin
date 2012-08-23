package com.acme.am.dao;

import java.util.Map;

import javax.ejb.Stateless;

import com.acme.am.dao.common.JpaGenericDao;
import com.acme.am.domain.User;

/**
 * @author <a href="mailto:marek.iwaszkiewicz@akquinet.de">Marek Iwaszkiewicz</a>
 * @author <a href="mailto:moritz.zimmer@adesso.de">Moritz Zimmer</a>
 */
@Stateless
public class UserDaoBean extends JpaGenericDao<User> implements UserDao {

  @Override
  public User loadByUsername(String username) {
    final Map<String, Object> paramMap = createParameterMap(User.PARAM_USERNAME, username);
    return findSingleByNamedQuery(User.QUERY_LOAD_BY_USERNAME, paramMap);
  }

}
