package com.acme.am.business.ut;

import static org.easymock.EasyMock.eq;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Rule;
import org.junit.Test;

import com.acme.am.business.UserService;
import com.acme.am.business.UserServiceBean;
import com.acme.am.dao.ContactDao;
import com.acme.am.dao.UserDao;
import com.acme.am.domain.Contact;
import com.acme.am.domain.User;
import com.acme.am.testsupport.LogInjector;

import de.akquinet.jbosscc.needle.annotation.ObjectUnderTest;
import de.akquinet.jbosscc.needle.junit.NeedleRule;
import de.akquinet.jbosscc.needle.mock.EasyMockProvider;

/**
 * @author <a href="mailto:moritz.zimmer@adesso.de">Moritz Zimmer</a>
 * @author <a href="mailto:marek.iwaszkiewicz@akquinet.de">Marek Iwaszkiewicz</a>
 */
public class UserServiceEasymockTest {

	@Rule
	public NeedleRule needleRule = new NeedleRule(new LogInjector());

	@ObjectUnderTest
	private final UserService userService = new UserServiceBean();

	private EasyMockProvider mockProvider = needleRule.getMockProvider();

	@Test
	public void testRemoveUserWithMockedServices() throws Exception {

		// mockProvider.resetAllToStrict();
		mockProvider.resetAllToNice();

		final User user = new User("xXx", "xXx", "vin", "disel", "triple@xXx.com");

		final ContactDao taskDao = needleRule.getInjectedObject(ContactDao.class);
		final UserDao userDao = needleRule.getInjectedObject(UserDao.class);

		final Contact contact1 = new Contact("a1", "a1", "a1@a1.x", null, user);
		final Contact contact2 = new Contact("b1", "b1", "b1@b1.x", null, user);

		final List<Contact> tasks = new ArrayList<Contact>();
		tasks.add(contact1);
		tasks.add(contact2);

		EasyMock.expect(taskDao.findForUser(eq(user))).andReturn(tasks);
		taskDao.delete(contact1);
		taskDao.delete(contact2);
		EasyMock.expectLastCall();

		EasyMock.expect(userDao.load(eq(user.getId()))).andReturn(user);
		userDao.delete(user);
		EasyMock.expectLastCall();

		mockProvider.replayAll();

		userService.removeUser(user);

		mockProvider.verifyAll();
	}

}
