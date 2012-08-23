package com.acme.am.business;

import java.util.List;

import javax.ejb.Local;

import com.acme.am.domain.Contact;
import com.acme.am.domain.User;

/**
 * @author <a href="mailto:moritz.zimmer@adesso.de">Moritz Zimmer</a>
 * @author <a href="mailto:marek.iwaszkiewicz@akquinet.de">Marek Iwaszkiewicz</a>
 */
@Local
public interface ContactService {

  List<Contact> loadContactsForUser(final User user);
  
  void saveContact(final Contact contact);

  void deleteContact(final Contact contact);

}
