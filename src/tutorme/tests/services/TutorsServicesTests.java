package tutorme.tests.services;

import com.jayway.restassured.response.Response;
import static com.jayway.restassured.RestAssured.given;

import tutorme.models.User;
import tutorme.models.core.BusinessObjectFactory;
import tutorme.tests.TestCaseServiceBase;

public class TutorsServicesTests extends TestCaseServiceBase {
	public void testGetJson() {
		User user = generateNewUserEmptyDatabase(new BusinessObjectFactory());
		String json = String.format("{\"ur_email\":\"%1$s\",\"ur_password\":\"%2$s\",\"ur_pk\":\"%3$s\",\"ur_firstname\":\"%4$s\",\"ur_lastname\":\"%5$s\"}", 
				user.getUR_Email(), user.getUR_Password(), user.PK, user.getUR_FirstName(), user.getUR_LastName());
		Response response =
	            given().
	                    header("Accept-Encoding", "application/json").
	            when().
	                    get("/rest/tutors");
		assertEquals("application/json", response.contentType());
		assertEquals(200, response.statusCode());
		assertTrue(response.asString().contains(json));
	}
	
	
}
