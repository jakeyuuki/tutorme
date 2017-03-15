package tutorme.controllers;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialException;

import tutorme.models.Tutor;
import tutorme.models.User;
import tutorme.models.core.BusinessObjectFactory;

@MultipartConfig
public class TutorManagementController extends SecuredHttpServlet {
	private static final long serialVersionUID = 1L;
	 
	public TutorManagementController() {
		super();
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException { 
		String action = request.getParameter("action");
		if("Save".equals(action)){
			try {
				save(request, response);
			} catch (SerialException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd;
		HttpSession session = request.getSession();
		String PK = (String)session.getAttribute("pk");  
		BusinessObjectFactory factory = new BusinessObjectFactory();
		User user = factory.loadByPK(PK, User.class);
		
		if (user != null){
			if(user.getUR_Activated()) {
				Tutor tutor = new BusinessObjectFactory().loadTop1(Tutor.getQueryFindUserPK(user.PK), Tutor.class);
				request.setAttribute("pic", tutor.getTR_Pic());
				request.setAttribute("summary", tutor.getTR_Summary());
				request.setAttribute("description", tutor.getTR_Description());
				rd = request.getRequestDispatcher("/tutorManagement.jsp");
			}
			else {
				request.setAttribute("email", user.getUR_Email());
				rd = request.getRequestDispatcher("/inactivatedUser.jsp");
			}
		}else{
			rd = request.getRequestDispatcher("/404.jsp");
		}
		rd.forward(request, response);
	}

	
	private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SerialException, SQLException{
		HttpSession session = request.getSession();
		String tutordesc = getSecuredParameter(request, "editor");
		String summary = getSecuredParameter(request, "summary");
		
		String pk = (String) session.getAttribute("pk");
		
		RequestDispatcher rd = request.getRequestDispatcher("/tutorManagement.jsp");
		
		String picturePath = savePicture(request, pk);
		
		saveDataTutor(request, picturePath, summary, pk, tutordesc);
		request.setAttribute("applicationSuccess", "Your tutor's information has been updated.");
		
		rd.forward(request, response);
	}

	private void saveDataTutor(HttpServletRequest request, String picturePath, String summary, String pk, String tutordesc) throws IOException, ServletException, FileNotFoundException {
		BusinessObjectFactory factory = new BusinessObjectFactory();
		Tutor tutor = factory.loadTop1(Tutor.getQueryFindUserPK(pk), Tutor.class);
		if(!picturePath.equals(""))	tutor.setTR_Pic(picturePath);
		tutor.setTR_Summary(summary);
		tutor.setTR_Description(tutordesc);
		factory.save();

		tutor = factory.loadTop1(Tutor.getQueryFindUserPK(pk), Tutor.class);
		request.setAttribute("pic", tutor.getTR_Pic());
		request.setAttribute("summary", tutor.getTR_Summary());
		request.setAttribute("description", tutor.getTR_Description());
	}

	private String savePicture(HttpServletRequest request, String pk)
			throws IOException, ServletException, FileNotFoundException {
		Part filePart = request.getPart("tutorpic");
		if(filePart == null) return "";
		
		InputStream tutorPicStream = filePart.getInputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int bytesRead;
		
		int size = 0;
		while ((bytesRead = tutorPicStream.read(buffer)) != -1) {
			baos.write(buffer, 0, bytesRead);
			size++;
		}
		if(size == 0) return "";
		byte[] content = baos.toByteArray();
		
		String absoluteWebPath = getServletContext().getRealPath("/");
		String serverPath = "/profilePics/" + pk + "." + getExtensionPath(filePart);
		String filePath = absoluteWebPath + serverPath;
		File file = new File(filePath);
		if(!file.exists()) {
			file.getParentFile().mkdirs();
		}
		FileOutputStream fos = new FileOutputStream(filePath);
		
		fos.write(content);
		fos.close();
		return serverPath;
	}
	
	private String getExtensionPath(Part filePart) {
		String fileName = filePart.getSubmittedFileName();
		String[] tokens = fileName.split("\\.");
		String extensionName = tokens[tokens.length - 1];
		return extensionName;
	}
}
