package tutorme.controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import tutorme.models.core.DBConnection;
import tutorme.util.MySQLUtils;

public class SecuredHttpServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected String getSecuredParameter(HttpServletRequest request, String param) {
		try {
			return request.getParameter(MySQLUtils.mysql_real_escape_string(DBConnection.connect(), param));
		} catch (Exception e) {
			e.printStackTrace();
			return request.getParameter(param);
		}
	}
}
