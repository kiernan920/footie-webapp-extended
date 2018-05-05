package utility.test;

import static org.junit.Assert.assertEquals;
import dao.UserDAO;
import tableEntities.User;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

	
	@RunWith(Arquillian.class)
	public class UsernamePasswordReturnTest {
		
		@Deployment
		public static Archive<?> createTestArchive() {
			return ShrinkWrap
					.create(JavaArchive.class, "Test.jar")
					.addClasses(User.class, UserDAO.class)
					.addPackage(User.class.getPackage())
					.addPackage(UserDAO.class.getPackage())
					.addAsManifestResource("META-INF/persistence.xml",
							"persistence.xml")
					.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		}
		private static boolean setUpIsDone = false;
		
		@EJB
		private UserDAO userDAO;
		private static final int ID1 = 13;
		
		@Before
		public void setUp() {
			if (setUpIsDone) {
				return;
			}
			setUpIsDone = true;
		}
		
		@Test
		public void testUsernamePassword() {
			User testUser1 = userDAO.getUserDataById(ID1);
			assertEquals("usertype is administrator","administrator",userDAO.checkUsernameAndPasswordReturnType(
					testUser1.getUsername()
					, testUser1.getPassword()));
		}
	}

	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		