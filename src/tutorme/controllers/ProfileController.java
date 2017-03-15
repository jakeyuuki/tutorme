package tutorme.controllers;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;

import tutorme.models.User;
import tutorme.models.core.BusinessObjectFactory;
import tutorme.util.MD5Hashing;

public class ProfileController extends SecuredHttpServlet {
	private static final long serialVersionUID = 1L;
	 
	public ProfileController() {
		super();
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException { 
		String action = request.getParameter("action");
		if("Update Details".equals(action)){
			updateUser(request, response);
        }
	}
	
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		RequestDispatcher rd;
		String pk = (String) session.getAttribute("pk");
		String firstname = getSecuredParameter(request, "firstname");
		String lastname = getSecuredParameter(request, "lastname");
		String address = getSecuredParameter(request, "address");
		String suburb = getSecuredParameter(request, "suburb");
		String state = getSecuredParameter(request, "state");
		String postcode = getSecuredParameter(request, "postcode");
		String country = getSecuredParameter(request, "country");
		String password = request.getParameter("password");
		
		BusinessObjectFactory factory = new BusinessObjectFactory();
		User user = factory.loadByPK(pk, User.class);
		user.setUR_FirstName(firstname);
		user.setUR_LastName(lastname);
		user.setUR_Address(address);
		user.setUR_Suburb(suburb);
		user.setUR_State(state);
		user.setUR_Postcode(postcode== "" ? null : Integer.parseInt(postcode));
		user.setUR_Country(country);
		if(!password.isEmpty()) user.setUR_Password(MD5Hashing.getMD5Hash(password));
		session.setAttribute("fullname", String.format("%1$s, %2$s", user.getUR_LastName(), user.getUR_FirstName()));
		rd = sendBackInputs(request, user);
		
		if(validate(request, firstname, lastname, address, suburb, state, postcode, country)){
			request.setAttribute("updateSuccess", "Profile update successful!");
			factory.save();
			
		}else{
			request.setAttribute("updateError", "Unsuccessful update profile due to bad inputs!");
		}
		rd = sendBackInputs(request, user);
		rd.forward(request, response);

	}
 
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd;
		HttpSession session = request.getSession();
		String PK = (String)session.getAttribute("pk");  
		BusinessObjectFactory factory = new BusinessObjectFactory();
		User user = factory.loadByPK(PK, User.class);
		
		if (user != null){
			if(user.getUR_Activated())
				rd = sendBackInputs(request, user);
			else {
				request.setAttribute("email", user.getUR_Email());
				rd = request.getRequestDispatcher("/inactivatedUser.jsp");
			}
		}else{
			rd = request.getRequestDispatcher("/404.jsp");
		}
		rd.forward(request, response);
	}
	
	private RequestDispatcher sendBackInputs(HttpServletRequest request, User user) {
		request.setAttribute("firstname", user.getUR_FirstName());
		request.setAttribute("email", user.getUR_Email());
		request.setAttribute("lastname", user.getUR_LastName());
		request.setAttribute("address", user.getUR_Address());
		request.setAttribute("suburb", user.getUR_Suburb());
		if(user.getUR_Postcode() != null && user.getUR_Postcode() != 0) request.setAttribute("postcode", user.getUR_Postcode());
		request.setAttribute("country", user.getUR_Country());
		request.setAttribute("state", user.getUR_State());
		request.setAttribute("isAdmin", user.getUR_IsAdmin());
		request.setAttribute("isTutor", user.getUR_IsTutor());
		return request.getRequestDispatcher("/useraccount.jsp");
	}
	
	
	private boolean validate(HttpServletRequest request, String firstName, String lastName, String address, String suburb, String state, String postcode, String country) {
		boolean validInputs = true;
		if (!firstName.matches("^[A-Z][A-Za-z\\_\\-\\ ]+$")){
			request.setAttribute("firstNameRegexError", true);
			validInputs = false;
		}
		if (firstName.length() > 30){
			request.setAttribute("firstNameLengthError", true);
			validInputs = false;
		}
		if (!lastName.matches("^[A-Z][A-Za-z\\_\\-\\ ]+$")){
			request.setAttribute("lastNameRegexError", true);
			validInputs = false;
		}
		if (lastName.length() > 30){
			request.setAttribute("lastNameLengthError", true);
			validInputs = false;
		}
		if (address.length() > 49){
			request.setAttribute("addressLengthError", true);
			validInputs = false;
		}
		if (suburb.length() > 49){
			request.setAttribute("suburbLengthError", true);
			validInputs = false;
		}
		if(!suburb.matches("^[A-Za-z\\_\\-\\ ]*$")){
			request.setAttribute("suburbRegexError", true);
			validInputs = false;
		}
		
		if (state.length() > 3){
			request.setAttribute("stateLengthError", true);
			validInputs = false;
		}
		if(!state.matches("^[A-Za-z\\_\\-\\ ]*$")){
			request.setAttribute("stateLengthError", true);
			validInputs = false;
		}
		
		if (!postcode.matches("^[0-9]*$")){
			request.setAttribute("postcodeRegexError", true);
			validInputs = false;
		}
		if (postcode.length() > 5){
			request.setAttribute("postcodeLengthError", true);
			validInputs = false;
		}
	
		if (country.length() > 49){
			request.setAttribute("countryLengthError", true);
			validInputs = false;
		}
		if(!country.matches("^[A-Za-z\\_\\-\\ ]*$")){
			request.setAttribute("countryRegexError", true);
			validInputs = false;
		}
		
		String password = request.getParameter("password");
		if (!password.isEmpty() && (!password.equals(request.getParameter("confirm_password")) || !password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$"))) {
			request.setAttribute("passwordError", "Please input a valid password!");
			validInputs = false;
		}
		return validInputs;
	}
}
