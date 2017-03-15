package tutorme.tests.models;

import org.json.JSONArray;

import tutorme.models.User;
import tutorme.models.User.UserSchema;
import tutorme.models.core.BusinessObjectFactory;
import tutorme.tests.TestCaseBase;

public class FactoryTests extends TestCaseBase {
	
	public void testLoadParsedData_Json(){
		BusinessObjectFactory factory = new BusinessObjectFactory();
		User user = generateNewUserEmptyDatabase(factory);
		
		String query = String.format("SELECT %1$s FROM %2$s WHERE %3$s = '%4$s';", 
				String.join(", ", new String[] { UserSchema.PK, UserSchema.UR_FirstName, UserSchema.UR_LastName, UserSchema.UR_Email, UserSchema.UR_Password}),
				UserSchema.TableName,
				UserSchema.UR_Email, testUR_Email);
		JSONArray list = factory.loadParsedData(query, JSONArray.class);
		assertEquals("[{\"ur_email\":\"test@test.test\",\"ur_password\":\"\",\"ur_pk\":\"" + user.PK + "\",\"ur_firstname\":\"Hello\",\"ur_lastname\":\"World\"}]", list.toString());
	}
}
