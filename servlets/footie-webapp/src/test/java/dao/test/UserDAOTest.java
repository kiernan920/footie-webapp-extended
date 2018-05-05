package dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;


	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
	@RunWith(Arquillian.class)
	public class UserDAOTest {

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

		private User user;
		private User user2;
		private static final int ID1 = 1;
		private static final int ID2 = 2;
		
		@Before
		public void setUp() {
			if (setUpIsDone) {
				return;
			}
			setUpIsDone = true;
			user = new User();
			user.setUsername("username");
			user.setAddress("address");
			user.setEmail("email");
			user.setFirstname("firstname");
			user.setGender("gender");
			user.setLastname("lastname");
			user.setPassword("password");
			user.setPhone("phone");
			user.setUserId(ID1);
			user.setUsertype("usertype");
			userDAO.save(user);
			
			user2 = new User();
			user2.setAddress("address2");
			user2.setEmail("email2");
			user2.setFirstname("firstname2");
			user2.setGender("gender2");
			user2.setLastname("lastname2");
			user2.setPassword("password2");
			user2.setPhone("phone2");
			user2.setUserId(ID2);
			user2.setUsertype("usertype2");
			user2.setUsername("username2");
			userDAO.save(user2);

		}

		@Test
		public void atestPersistUserToDB() {
			User testUser = userDAO.getUserDataById(ID1);
			assertNotNull(testUser);
			assertNotNull(testUser.getUserId());
			assertNotNull(testUser.getPassword());
		}

		@Test
		public void btestUserFindById() {
			User testUser = userDAO.getUserDataById(ID1);
			assertEquals("Data fetch = data persisted",
					testUser.getPassword(), "password");
			assertEquals("Data fetch = data persisted", testUser.getPassword(),
					"password");
		}

		@Test
		public void ctestDeleteUserFindAll() {
			User testUser1 = userDAO.getUserDataById(ID1);
			User testUser2 = userDAO.getUserDataById(ID2);
			 userDAO.delete(testUser1);
			 userDAO.delete(testUser2);
			testUser1 = userDAO.getUserDataById(ID1);
			testUser2 = userDAO.getUserDataById(ID2);
			assertTrue(testUser1 == null);
			assertTrue(testUser1 == null);
		}
}
