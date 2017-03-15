package tutorme.tests.models;

import java.util.Date;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

import org.junit.Test;

import tutorme.models.User;
import tutorme.models.User.UserSchema;
import tutorme.models.core.BusinessObjectFactory;
import tutorme.tests.TestCaseBase;

public class UserTests extends TestCaseBase {
	String testUR_Email = "test@test.test";
	
	@Test
	public void testEndToEndUserTest() throws InterruptedException {
		// existingUsers with testUR_Email = load testUR_Email
		BusinessObjectFactory factory = new BusinessObjectFactory();
		String query = String.format("Select * from %1$s where %2$s = '%3$s';", UserSchema.TableName, UserSchema.UR_Email, testUR_Email);
		List<User> users = factory.load(query, User.class);
		
		// if(exist) delete testUR_Email
		for (User user : users) {
			user.delete();
		}
		factory.save();
		
		factory = new BusinessObjectFactory();
		users = factory.load(query, User.class);
		assertEquals(0, users.size());
		
		// create testUR_Email
		factory = new BusinessObjectFactory();
		User user = factory.create(User.class);
		user.setMandatoryFields("Hello", "World", testUR_Email, "");
		factory.save();
		
		// load
		factory = new BusinessObjectFactory();
		users = factory.load(String.format("Select * from %1$s where %2$s = '%3$s';", UserSchema.TableName, UserSchema.UR_Email, testUR_Email), User.class);
		assertEquals(1, users.size());
		
		// loadByPK by PK
		factory = new BusinessObjectFactory();
		User user1 = factory.loadByPK(user.getPK(), User.class);
		assertNotNull(user1);
		
		// delete
		user1.delete();
		factory.save();
		
		// loadByPK
		factory = new BusinessObjectFactory();
		user1 = factory.loadByPK(user.getPK(), User.class);
		assertNull(user1);
		
		// create testUR_Email
		factory = new BusinessObjectFactory();
		user = factory.create(User.class);
		user.setMandatoryFields("Hello", "World", testUR_Email, "");
		factory.save();
		
		// update
		user.setUR_Country("Australia");
		
		// reload => load does not update change
		ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
		Date oneSecondAgo = Date.from(utc.minusSeconds(1).toInstant());
		user.reload();
		factory.save();
		
		// loadByPK
		factory = new BusinessObjectFactory();
		user1 = factory.loadByPK(user.getPK(), User.class);
		assertNotNull(user1);
		assertEquals(null, user1.getUR_Country()); // null value in column -> String: null
		assertEquals(null, user1.getUR_Postcode()); // null value in column -> int: 0
		assertTrue(user1.getUR_RegisteredTimeUTC().after(oneSecondAgo));
		assertTrue(user1.getUR_UpdatedTimeUTC().after(oneSecondAgo));
		
		// update
		user1.setUR_Country("Australia");
		oneSecondAgo = Date.from(utc.minusSeconds(1).toInstant());
		factory.save();
		
		// load => does change this time
		factory = new BusinessObjectFactory();
		user = factory.loadByPK(user1.getPK(), User.class);
		assertNotNull(user);
		assertEquals("Australia", user1.getUR_Country());
		assertTrue(user.getUR_UpdatedTimeUTC().after(oneSecondAgo));
	}
}
