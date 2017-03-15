package tutorme.controllers;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;

import tutorme.models.Tutor;
import tutorme.models.User;
import tutorme.models.core.BusinessObjectFactory;

public class TutorController extends SecuredHttpServlet {
	private static final long serialVersionUID = 1L;
	 
	public TutorController() {
		super();
	}
 
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String tutorPk = getSecuredParameter(request, "id");
		
		RequestDispatcher rd;
 
		if(tutorPk == null) {
			rd = request.getRequestDispatcher("/404.jsp");
		}
		else {
			BusinessObjectFactory factory = new BusinessObjectFactory();
			Tutor tutor = factory.loadByPK(tutorPk, Tutor.class);
			if(tutor == null) {
				rd = request.getRequestDispatcher("/404.jsp");
			}
			else {
				User user = factory.loadByPK(tutor.getTR_UR_PK(), User.class);
				rd = request.getRequestDispatcher("/indirectAccessiblePages/tutor.jsp");
				request.setAttribute("tutorpic", tutor.getTR_Pic());
				request.setAttribute("tutorname", user.getUR_FirstName() + " " + user.getUR_LastName());
				request.setAttribute("tutordesc", tutor.getTR_Description());	
				request.setAttribute("tutoremail", user.getUR_Email());
				request.setAttribute("tutorcountry", user.getUR_Country());				

			}
		}
		
		rd.forward(request, response);
	}
}
