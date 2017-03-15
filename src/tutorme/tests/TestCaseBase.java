package tutorme.tests;

import java.util.List;

import junit.framework.TestCase;
import tutorme.models.User;
import tutorme.models.User.UserSchema;
import tutorme.models.core.BusinessObjectFactory;
import tutorme.models.core.JUnit;

public abstract class TestCaseBase extends TestCase {	
	protected String testUR_Email = "test@test.test";
	
	@Override
    protected void setUp() throws Exception {
        super.setUp();
        JUnit.ON = true;
    }
	
	@Override
    protected void tearDown() throws Exception {
        super.tearDown();
        JUnit.ON = false;
    }
	
	protected User generateNewUserEmptyDatabase(BusinessObjectFactory factory) {
		String query = String.format("Select * from %1$s where %2$s = '%3$s';", UserSchema.TableName, UserSchema.UR_Email, testUR_Email);
		List<User> users = factory.load(query, User.class);
		
		// if(exist) delete testUR_Email
		for (User user : users) {
			user.delete();
		}
		factory.save();
		User user = factory.create(User.class);
		user.setMandatoryFields("Hello", "World", testUR_Email, "");
		factory.save();
		return user;
	}
}
