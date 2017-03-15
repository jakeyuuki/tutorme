package tutorme.tests.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.mockito.Mockito;

import tutorme.controllers.RegisterController;
import tutorme.models.User;
import tutorme.models.UserActivation;
import tutorme.models.User.UserSchema;
import tutorme.models.core.BusinessObjectFactory;
import tutorme.services.Activation;
import tutorme.tests.TestCaseBase;

public class RegisterControllerTests extends TestCaseBase{
	public void testRegisterAndActivation() throws ServletException, IOException, URISyntaxException {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);       
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        RequestDispatcher rd = Mockito.mock(RequestDispatcher.class);
        TestRegisterController controller = Mockito.spy(new TestRegisterController());
        
        // Remove the activation key and user if exist.
        BusinessObjectFactory factory = new BusinessObjectFactory();
        String query = String.format("Select * from %1$s where %2$s = '%3$s';", UserSchema.TableName, UserSchema.UR_Email, testUR_Email);
		List<User> users = factory.load(query, User.class);
		for (User user : users) {
			List<UserActivation> activationKeys = factory.load(UserActivation.getQueryFindByUserId(user.PK), UserActivation.class);
	        for (UserActivation activationKey : activationKeys) {
	        	activationKey.delete();
			}
			user.delete();
		}
        factory.save();
        
        Mockito.stub(request.getParameter("email")).toReturn(testUR_Email);
        Mockito.stub(request.getParameter("firstname")).toReturn("Test");
        Mockito.stub(request.getParameter("lastname")).toReturn("Test");
        Mockito.stub(request.getParameter("password")).toReturn("Test");
        Mockito.doNothing().when(rd).forward(request, response);
        Mockito.doReturn(rd).when(controller).getRequestDispatcher(Mockito.any(), Mockito.any());
        Mockito.doNothing().when(controller).sendEmail(Mockito.any(), Mockito.any(), Mockito.any());
        
        controller.doPost(request, response);
        
        User user = factory.loadTop1(User.getQueryFindEmail(testUR_Email), User.class);
        assertNotNull(user);
        assertEquals(false, user.getUR_Activated());
        UserActivation activationKey = factory.loadTop1(UserActivation.getQueryFindByUserId(user.PK), UserActivation.class);
        assertNotNull(activationKey);
        
        new Activation().activate(activationKey.PK);
        
        user.reload();
        assertEquals(true, user.getUR_Activated());
	}
	
	@SuppressWarnings("serial")
	class TestRegisterController extends RegisterController {
		protected void sendEmail(HttpServletRequest request, String email, String activationCode) {
			super.sendEmail(request, email, activationCode);
		}
		
		protected RequestDispatcher getRequestDispatcher(HttpServletRequest request, String url) {
			return super.getRequestDispatcher(request, url);
		}
	}
}
