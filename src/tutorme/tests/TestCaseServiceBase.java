package tutorme.tests;

import org.junit.After;
import org.junit.Before;

import com.jayway.restassured.RestAssured;

import tutorme.models.core.JUnit;


public abstract class TestCaseServiceBase extends TestCaseBase {	
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		 // set default port for REST-assured
		JUnit.ON = false;
	    RestAssured.port = 8080;

	    // set default URI for REST-assured.
	    // In integration tests, this would most likely point to localhost.
	    RestAssured.baseURI = "http://localhost";
    }
	
	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}
}
