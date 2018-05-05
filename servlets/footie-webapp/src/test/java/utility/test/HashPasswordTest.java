package utility.test;

import static org.junit.Assert.assertEquals;
import utility.HashPassword;

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
	public class HashPasswordTest {
		
		@Deployment
		public static Archive<?> createTestArchive() {
			return ShrinkWrap
					.create(JavaArchive.class, "Test.jar")
					.addClasses(HashPassword.class)
					.addPackage(HashPassword.class.getPackage())
					.addAsManifestResource("META-INF/persistence.xml",
							"persistence.xml")
					.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

		}

		private static boolean setUpIsDone = false;
		
		private String passwordBeforeHash = "no";
		private String passwordAfterHash = "7f.a3.b7.67.c4.60.b5.4a.2b.e4.d4.90.30.b3.49.c7.";
		
		@Before
		public void setUp() {
			if (setUpIsDone) {
				return;
			}
			setUpIsDone = true;
		}
		

		@Test
		public void testHashPassword() {
			HashPassword hp = new HashPassword();
			String hashPassword = hp.hashPassword(this.passwordBeforeHash);
			assertEquals(hashPassword, passwordAfterHash);
		}

}
