package tutorme.controllers;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;

import tutorme.models.Course;
import tutorme.models.core.BusinessObjectFactory;

public class CourseController extends SecuredHttpServlet {
	private static final long serialVersionUID = 1L;
	 
	public CourseController() {
		super();
	}
 
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String coursePK = getSecuredParameter(request, "id");
		
		RequestDispatcher rd;
 
		if(coursePK == null) {
			rd = request.getRequestDispatcher("/404.jsp");
		}
		else {
			BusinessObjectFactory factory = new BusinessObjectFactory();
			Course course = factory.loadByPK(coursePK, Course.class);
			if(course == null) {
				rd = request.getRequestDispatcher("/404.jsp");
			}
			else {
				rd = request.getRequestDispatcher("/indirectAccessiblePages/course.jsp");
				request.setAttribute("course", course);
			}
		}
		
		rd.forward(request, response);
	}
}
