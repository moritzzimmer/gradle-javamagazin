package com.acme.am.web.security;

import org.jboss.seam.security.Identity;
import org.jboss.seam.security.annotations.Secures;

import com.acme.am.web.annotations.UserLoggedIn;

/**
 * @author <a href="mailto:moritz.zimmer@adesso.de">Moritz Zimmer</a>
 * @author <a href="mailto:marek.iwaszkiewicz@akquinet.de">Marek Iwaszkiewicz</a>
 */
public class SecurityRules {

  @Secures
  @UserLoggedIn
  public boolean isUserLoggedIn(Identity identity) {
    return identity.getUser() != null;
  }

}
