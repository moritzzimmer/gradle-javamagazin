package com.acme.am.business.it;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.acme.am.business.UserService;
import com.acme.am.business.it.util.ArquillianHelper;
import com.acme.am.domain.User;

/**
 * You must run the test via gradle first! Within the gradle build, libraries needed for the ShrinkWrap deployment are
 * copied from the local gradle file repo into the build directory of the project.
 * 
 * @author <a href="mailto:moritz.zimmer@adesso.de">Moritz Zimmer</a>
 * @author <a href="mailto:marek.iwaszkiewicz@akquinet.de">Marek Iwaszkiewicz</a>
 */
@RunWith(Arquillian.class)
public class UserServiceIntegrationTest {

	@Inject
	private UserService userService;

	@Deployment
	public static WebArchive createWebArchive() {
		final WebArchive webArchive = ArquillianHelper.testWebArchive;
		return webArchive;
	}

	@Test
	public void testLoadByUsername() throws Exception {

		final User user1 = new User("xXx", "xXx", "vin", "disel", "triple@xXx.com");
		final User user2 = new User("spiderman", "spiderman", "peter", "parker", "peter.parker@marvel.com");

		userService.saveUser(user1);
		userService.saveUser(user2);

		User xXx = userService.loadByUsername("xXx");
		Assert.assertNotNull(xXx);

		User spiderman = userService.loadByUsername("spiderman");
		Assert.assertNotNull(spiderman);

		User hulk = userService.loadByUsername("hulk");
		Assert.assertNull(hulk);
	}

}
