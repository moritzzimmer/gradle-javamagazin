package com.acme.am.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.acme.am.dao.ContactDao;
import com.acme.am.domain.Contact;
import com.acme.am.domain.User;

/**
 * @author <a href="mailto:moritz.zimmer@adesso.de">Moritz Zimmer</a>
 * @author <a href="mailto:marek.iwaszkiewicz@akquinet.de">Marek Iwaszkiewicz</a>
 */
@Stateless
public class ContactServiceBean implements ContactService {

  @EJB
  private ContactDao contactDao;
  
  // -------------- interface method implementations ------------------------------------

	@Override
  public List<Contact> loadContactsForUser(User user) {
	  return contactDao.findForUser(user);
  }

	@Override
  public void saveContact(Contact contact) {
		contactDao.persist(contact);
  }

	@Override
  public void deleteContact(Contact contact) {
		contactDao.delete(contact);
  }


}
