package com.acme.am.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import com.acme.am.dao.common.JpaGenericDao;
import com.acme.am.domain.Contact;
import com.acme.am.domain.User;

/**
 * @author <a href="mailto:moritz.zimmer@adesso.de">Moritz Zimmer</a>
 * @author <a href="mailto:marek.iwaszkiewicz@akquinet.de">Marek Iwaszkiewicz</a>
 */
@Stateless
public class ContactDaoBean extends JpaGenericDao<Contact> implements ContactDao {

	@Override
	public List<Contact> findForUser(User user) {
		final Map<String, Object> paramMap = createParameterMap(Contact.PARAM_USER, user);
		return findByNamedQuery(Contact.QUERY_LOAD_FOR_USER, paramMap);
	}

}
