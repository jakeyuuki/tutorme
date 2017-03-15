package tutorme.controllers;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;

import tutorme.models.User;
import tutorme.models.UserActivation;
import tutorme.models.core.BusinessObjectFactory;
import tutorme.util.MD5Hashing;
import tutorme.util.Mailer;

public class RegisterController extends SecuredHttpServlet {
	private static final long serialVersionUID = 1L;
	
	StringBuffer emailText = new StringBuffer("Please click the below URL to confirm your registration in TutorMe application.");
	final static String url = "http://localhost:8080/rest/activate?activationCode=";
	
	public RegisterController() {
		super();
	}
 
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String email = getSecuredParameter(request, "email");
		String firstName = getSecuredParameter(request, "firstname");
		String lastName = getSecuredParameter(request, "lastname");
		
		RequestDispatcher rd;
		if(validate(request, email, firstName, lastName))
		{
			BusinessObjectFactory factory = new BusinessObjectFactory();
			User user = factory.loadTop1(User.getQueryFindEmail(email), User.class);
			if(user != null) {
				if(isUnactivated(request, user)) { // The user is existed, but not activated -> re-send email.
					rd = resendEmailActivation(request, user, factory, email);
				}
				else { // The user is existed, forward back to myAccount.jsp.
					request.setAttribute("emailError", true);
					rd = sendBackInputs(request, email, firstName, lastName);
				}
			}
			else {
				String activationCode = createNewUser(request, email, firstName, lastName, factory);
				rd = sendEmailActivation(request, email, firstName, activationCode);
			}
		}
		else {
			rd = sendBackInputs(request, email, firstName, lastName);
		}
		
		rd.forward(request, response);
	}

	private boolean validate(HttpServletRequest request, String email, String firstName, String lastName) {
		boolean validInputs = true;
		if (firstName != null && !firstName.matches("^[A-Z][A-Za-z_-]+$")){
			request.setAttribute("firstNameError", true);
			validInputs = false;
		}
		if (lastName != null && !lastName.matches("^[A-Z][A-Za-z_-]+$")){
			request.setAttribute("lastNameError", true);
			validInputs = false;
		}
		if (email != null && !email.matches("^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$")){
			request.setAttribute("emailError", true);
			validInputs = false;
		}
		String password = request.getParameter("password");
		if (password != null && (!password.equals(request.getParameter("confirm_password")) || !password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$"))) {
			request.setAttribute("passwordError", true);
			validInputs = false;
		}
		return validInputs;
	}
	
	private RequestDispatcher sendBackInputs(HttpServletRequest request, String email, String firstName, String lastName) {
		request.setAttribute("email", email);
		request.setAttribute("firstname", firstName);
		request.setAttribute("lastname", lastName);
		request.setAttribute("password", request.getParameter("password"));
		request.setAttribute("confirm_password", request.getParameter("confirm_password"));
		return getRequestDispatcher(request, "/myAccount.jsp");
	}
	
	private String createNewUser(HttpServletRequest request, String email, String firstName, String lastName,
			BusinessObjectFactory factory) {
		User newUser = factory.create(User.class);
		newUser.setMandatoryFields(firstName, lastName, email, MD5Hashing.getMD5Hash(request.getParameter("password")));
		UserActivation activation = factory.create(UserActivation.class);
		activation.setMandatoryFields(newUser.PK);
		factory.save();
		return activation.PK;
	}
	
	protected boolean isUnactivated(HttpServletRequest request, User user){
		return request.getParameter("reactivate") != null && !user.getUR_Activated();
	}
	
	protected RequestDispatcher resendEmailActivation(HttpServletRequest request, User user, BusinessObjectFactory factory, String email){
		UserActivation activation = factory.loadTop1(UserActivation.getQueryFindByUserId(user.PK), UserActivation.class);
		if(activation == null) {
			activation = factory.create(UserActivation.class);
			activation.setMandatoryFields(user.PK);
			factory.save();
		}
		return sendEmailActivation(request, email, user.getUR_FirstName(), activation.PK);
	}

	protected RequestDispatcher sendEmailActivation(HttpServletRequest request, String email, String firstName, String activationCode) {
		request.setAttribute("email", email);
		request.setAttribute("firstName", firstName);
		sendEmail(request, email, activationCode);
		return getRequestDispatcher(request, "/registerAction.jsp");
	}

	protected void sendEmail(HttpServletRequest request, String email, String activationCode) {
		StringBuffer emailText = new StringBuffer("Please click the below URL to confirm your registration in TutorMe application: ");
		emailText.append(url);
		emailText.append(activationCode);
		Mailer.setPropsAndSendEmail(email, emailText.toString());
	}
	
	protected RequestDispatcher getRequestDispatcher(HttpServletRequest request, String url) {
		return request.getRequestDispatcher(url);
	}
}
