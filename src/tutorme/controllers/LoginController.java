package tutorme.controllers;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;

import tutorme.models.Tutor;
import tutorme.models.User;
import tutorme.models.core.BusinessObjectFactory;
import tutorme.util.MD5Hashing;


public class LoginController extends SecuredHttpServlet {
	private static final long serialVersionUID = 1L;
	 
	public LoginController() {
		super();
	}
 
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException { 
		String action = request.getParameter("action");
        if("Sign in".equals(action)){
        	signin(request, response);
        }
        if("signout".equals(action)){
        	signout(request, response);
        } 
	}
	
	private void signin(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		String email = getSecuredParameter(request, "email");
		String password = MD5Hashing.getMD5Hash(request.getParameter("password"));
		RequestDispatcher rd;
		BusinessObjectFactory factory = new BusinessObjectFactory();
		User user = factory.loadTop1(User.getQueryAuthenticate(email, password), User.class);
		if(user != null) {
			if(user.getUR_IsActive()) {
			authenticate(user, request, response);
			rd = request.getRequestDispatcher("/loginAction.jsp");
			request.setAttribute("firstName", user.getUR_FirstName());
			}
			else {
				rd = request.getRequestDispatcher("/isNotActive.jsp");
			}
		}
		else {
			rd = request.getRequestDispatcher("/myAccount.jsp");
			user = factory.loadTop1(User.getQueryFindEmail(email), User.class);
			request.setAttribute("loginError", user != null ? "Invalid password." : "Invalid email.");
		}
		
		rd.forward(request, response);
	}
	
	private void signout(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.getSession().invalidate();
		response.sendRedirect("/");
	}

	void authenticate(User user, HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		
		BusinessObjectFactory factory = new BusinessObjectFactory();
		Tutor tutor = factory.loadTop1(Tutor.getQueryFindUserPK(user.getPK()), Tutor.class);
		
		if (tutor!=null){
			session.setAttribute("applicationSent", true);
			session.setAttribute("tutorPK", tutor.PK);
		}else{
			session.setAttribute("applicationSent", false);
		}
		
		session.setAttribute("pk", user.getPK());
		session.setAttribute("fullname", String.format("%1$s, %2$s", user.getUR_LastName(), user.getUR_FirstName()));
		session.setAttribute("isAdmin", user.getUR_IsAdmin());
		session.setAttribute("isTutor", user.getUR_IsTutor());

	}
	
	public static boolean isSessionReady(HttpSession session) {
		return session.getAttribute("pk") != null && session.getAttribute("fullname") != null &&
				session.getAttribute("isAdmin") != null && session.getAttribute("isTutor") != null;
	}
}
