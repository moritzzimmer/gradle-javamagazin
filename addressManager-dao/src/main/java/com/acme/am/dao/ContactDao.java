package com.acme.am.dao;

import java.util.List;

import javax.ejb.Local;

import com.acme.am.dao.common.GenericDao;
import com.acme.am.domain.Contact;
import com.acme.am.domain.User;

/**
 * @author <a href="mailto:moritz.zimmer@adesso.de">Moritz Zimmer</a>
 * @author <a href="mailto:marek.iwaszkiewicz@akquinet.de">Marek Iwaszkiewicz</a>
 */
@Local
public interface ContactDao extends GenericDao<Contact> {

	List<Contact> findForUser(User user);

}
