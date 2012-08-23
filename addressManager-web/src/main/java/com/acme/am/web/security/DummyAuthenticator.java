package com.acme.am.web.security;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.seam.security.BaseAuthenticator;
import org.jboss.seam.security.Credentials;
import org.jboss.solder.logging.Logger;
import org.picketlink.idm.impl.api.PasswordCredential;
import org.picketlink.idm.impl.api.model.SimpleUser;

import com.acme.am.business.UserService;
import com.acme.am.domain.User;

/**
 * @author <a href="mailto:moritz.zimmer@adesso.de">Moritz Zimmer</a>
 * @author <a href="mailto:marek.iwaszkiewicz@akquinet.de">Marek Iwaszkiewicz</a>
 */
@Named("dummyAuthenticator")
@SessionScoped
public class DummyAuthenticator extends BaseAuthenticator implements Serializable {

  private static final long serialVersionUID = 1L;

	@Inject
  private Logger log;

  @Inject
  private Credentials credentials;
  
  @Inject
  private UserService userService;
  
	@Override
  public void authenticate() {
	 
		String username = credentials.getUsername();
		String password = ((PasswordCredential)credentials.getCredential()).getValue();
		
		log.debugv("authenticating user {0}", username);
	  
		User loadByUsername = userService.loadByUsername(username);
		
		if(loadByUsername != null && loadByUsername.getPassword().equals(password)) {
			setStatus(AuthenticationStatus.SUCCESS);
			setUser(new SimpleUser(username));
			log.debugv("user {0} logged in", username);
			
		} else {
			setStatus(AuthenticationStatus.FAILURE);
			log.debugv("authenticating for user {0} failed", username);
		}
		
  }

}
