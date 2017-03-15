package tutorme.tests.models;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import tutorme.models.*;
import tutorme.models.User.UserSchema;
import tutorme.models.Tutor.TutorSchema;

import tutorme.models.core.BusinessObjectFactory;
import tutorme.tests.TestCaseBase;

public class TutorTests extends TestCaseBase {
	
	public void testEndToEndTutorTest() throws InterruptedException {
		// Return new User and Factory is empty now
		BusinessObjectFactory factory = new BusinessObjectFactory();
		User user = generateExampleDatabase(factory);
		
		// Load user from DB
		user = factory.loadByPK(user.PK, User.class);
		user.setUR_IsTutor(true);
		
		Tutor tutor = factory.create(Tutor.class);
		tutor.setMandatoryFields(user.PK, "Summary test");
		ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
		Date oneSecondAgo = Date.from(utc.minusSeconds(1).toInstant());
		factory.save();
		
		String queryTutor = String.format("Select * from %1$s where %2$s = '%3$s';", TutorSchema.TableName, TutorSchema.TR_UR_PK, user.PK);
		List<Tutor> tutors = factory.load(queryTutor, Tutor.class);
		assertEquals(1, tutors.size());
		
		tutor = tutors.get(0);
		assertTrue(tutor.getTR_RegisteredTimeUTC().after(oneSecondAgo));
		assertTrue(tutor.getTR_UpdatedTimeUTC().after(oneSecondAgo));
	}
	
	private User generateExampleDatabase(BusinessObjectFactory factory) {
		
		String queryUser = String.format("Select * from %1$s where %2$s = '%3$s';", UserSchema.TableName, UserSchema.UR_Email, testUR_Email);
		
		List<User> users = factory.load(queryUser, User.class);

		// if(exist) delete testUR_Email
		for (User user : users) {
			List<Tutor> tutors = factory.load(String.format("Select * from %1$s where %2$s = '%3$s';", TutorSchema.TableName, TutorSchema.TR_UR_PK, user.PK)
			, Tutor.class);
			for (Tutor tutor : tutors) {
				tutor.delete();
			}
			user.delete();
		}
		factory.save();
		User user = factory.create(User.class);
		user.setMandatoryFields("Hello", "World", testUR_Email, "");
		factory.save();
		return user;
	}
}
