package com.acme.am.business.it.util;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import com.acme.am.business.UserService;
import com.acme.am.business.UserServiceBean;
import com.acme.am.dao.ContactDao;
import com.acme.am.dao.ContactDaoBean;
import com.acme.am.dao.UserDao;
import com.acme.am.dao.UserDaoBean;
import com.acme.am.dao.common.GenericDao;
import com.acme.am.dao.common.JpaGenericDao;
import com.acme.am.domain.User;

/**
 * @author <a href="mailto:moritz.zimmer@adesso.de">Moritz Zimmer</a>
 * @author <a href="mailto:marek.iwaszkiewicz@akquinet.de">Marek Iwaszkiewicz</a>
 */
public final class ArquillianHelper {

	public static final WebArchive testWebArchive = ShrinkWrap
	    .create(WebArchive.class, "test.war")
	    .addClasses(JpaGenericDao.class, GenericDao.class)
	    .addClasses(UserService.class, UserServiceBean.class)
	    .addClasses(UserDao.class, UserDaoBean.class)
	    .addClasses(ContactDao.class, ContactDaoBean.class)
	    .addPackage(User.class.getPackage())
	    .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
//	    .addAsLibraries(MavenArtifactResolver.resolve("org.jboss.solder:solder-api:3.1.1.Final"),
//	        MavenArtifactResolver.resolve("org.jboss.solder:solder-impl:3.1.1.Final"),
//	        MavenArtifactResolver.resolve("org.jboss.solder:solder-logging:3.1.1.Final"))
	    .addAsLibraries(FileSystemArtifactResolver.resolveFromLocalBuildDirectory())
	    .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

}
