package com.acme.am.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.solder.logging.Logger;
import org.picketlink.idm.impl.api.model.SimpleUser;

import com.acme.am.business.ContactService;
import com.acme.am.business.UserService;
import com.acme.am.domain.Contact;
import com.acme.am.domain.User;
import com.acme.am.web.qualifier.CurrentUser;

/**
 * @author <a href="mailto:moritz.zimmer@adesso.de">Moritz Zimmer</a>
 * @author <a href="mailto:marek.iwaszkiewicz@akquinet.de">Marek Iwaszkiewicz</a>
 */
@Named("contactlistController")
@SessionScoped
public class ContactlistController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger log;

	@Inject
	@CurrentUser
	private SimpleUser loggedInUser;

	@Inject
	private ContactService contactService;

	@Inject
	private UserService userService;

	// presentation model
	private List<Contact> contacts;

	// -------------- getter / setter -----------------------------------------------------

	public List<Contact> getContacts() {
		if (contacts == null) {
			log.debug("contact list is empty");
			
			log.debugv("logged in user is {0}", loggedInUser.getId());
			User user = userService.loadByUsername(loggedInUser.getId());
			
			contacts = contactService.loadContactsForUser(user);
			log.debugv("user {0} has {1} contacts", user.getLogin(), contacts.size());
		}
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public boolean isContactlistIsEmpty() {
		return getContacts().size() == 0;
	}
}
