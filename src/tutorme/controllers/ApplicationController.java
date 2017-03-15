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

import tutorme.models.Certification;
import tutorme.models.Tutor;
import tutorme.models.User;
import tutorme.models.core.BusinessObjectFactory;

@MultipartConfig
public class ApplicationController extends SecuredHttpServlet {
	private static final long serialVersionUID = 1L;
	 
	public ApplicationController() {
		super();
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException { 
		String action = request.getParameter("action");
		if("Apply as Tutor".equals(action)){
			try {
				sendApplication(request, response);
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

	
	private void sendApplication(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SerialException, SQLException{
		HttpSession session = request.getSession();
		String tutordesc = getSecuredParameter(request, "editor");

		String tutorteach = getSecuredParameter(request, "tutorteach");
		String certname = getSecuredParameter(request, "certname");
		String certdescription = getSecuredParameter(request, "certdescription");
		String firstname = getSecuredParameter(request, "firstname");
		String lastname = getSecuredParameter(request, "lastname");
		String address = getSecuredParameter(request, "address");
		String suburb = getSecuredParameter(request, "suburb");
		String state = getSecuredParameter(request, "state");
		String postcode = getSecuredParameter(request, "postcode");
		String country = getSecuredParameter(request, "country");
		String summary = getSecuredParameter(request, "summary");
		
		String pk = (String) session.getAttribute("pk");
		
		RequestDispatcher rd = request.getRequestDispatcher("/applicationSuccess.jsp");
		
		if(validate(request, firstname, lastname, address, suburb, state, postcode, country)) {
			saveDataNewTutor(request, tutorteach, certname, certdescription, firstname, lastname, address, suburb, state,
					postcode, country, summary, pk, tutordesc);
			session.setAttribute("applicationSent", true);
		}
		else
			request.setAttribute("updateError", "Unsuccessful tutor application due to bad inputs!");
		
		rd.forward(request, response);
	}

	private void saveDataNewTutor(HttpServletRequest request, String tutorteach, String certname,
			String certdescription, String firstname, String lastname, String address, String suburb, String state,
			String postcode, String country, String summary, String pk, String tutordesc) throws IOException, ServletException, FileNotFoundException {
		
		BusinessObjectFactory factory = new BusinessObjectFactory();
		User user = factory.loadByPK(pk, User.class);
		
		String picturePath = savePicture(request, pk);
		String scanPath = saveCertScan(request, pk);

		saveTutorData(tutorteach, certname, certdescription, firstname, lastname, address, suburb, state, postcode,
				country, summary, pk, factory, user, picturePath, scanPath, tutordesc);
		
		factory.save();
	}

	private void saveTutorData(String tutorteach, String certname, String certdescription, String firstname,
			String lastname, String address, String suburb, String state, String postcode, String country, String summary, String pk,
			BusinessObjectFactory factory, User user, String filePath, String certPath, String tutordesc) {
		
		Tutor tutor = createNewTutor(tutorteach, pk, factory, summary, tutordesc, filePath);
		createCertification(certname, certdescription, factory, tutor, certPath);
		updateUserInfo(firstname, lastname, address, suburb, state, postcode, country, user);
	}

	private void updateUserInfo(String firstname, String lastname, String address, String suburb, String state,
			String postcode, String country, User user) {
		user.setUR_FirstName(firstname);
		user.setUR_LastName(lastname);
		user.setUR_Address(address);
		user.setUR_Suburb(suburb);
		user.setUR_State(state);
		user.setUR_Country(country);
		user.setUR_Postcode(postcode== "" ? null : Integer.parseInt(postcode));
	}

	private void createCertification(String certname, String certdescription, BusinessObjectFactory factory,
			Tutor tutor, String certPath) {
		Certification certification = factory.create(Certification.class);
		certification.setMandatoryFields(certname, certdescription);
		certification.setCN_TR_PK(tutor.getPK());
		certification.setCN_Scan(certPath);
	}

	private Tutor createNewTutor(String tutorteach, String pk, BusinessObjectFactory factory, String summary, String tutordesc, String filePath) {
		Tutor tutor = factory.create(Tutor.class);
		tutor.setMandatoryFields(pk, tutorteach);
		tutor.setTR_Summary(tutorteach);
		tutor.setTR_Pic(filePath);
		tutor.setTR_Summary(summary);
		tutor.setTR_Description(tutordesc);
		return tutor;
	}

	private String savePicture(HttpServletRequest request, String pk)
			throws IOException, ServletException, FileNotFoundException {
		Part filePart = request.getPart("tutorpic");

		InputStream tutorPicStream = filePart.getInputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int bytesRead;

		while ((bytesRead = tutorPicStream.read(buffer)) != -1) {
			baos.write(buffer, 0, bytesRead);
		}

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
	
	private String saveCertScan(HttpServletRequest request, String pk)
			throws IOException, ServletException, FileNotFoundException {
		Part filePart = request.getPart("certscan");

		InputStream certScanStream = filePart.getInputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int bytesRead;

		while ((bytesRead = certScanStream.read(buffer)) != -1) {
			baos.write(buffer, 0, bytesRead);
		}

		byte[] content = baos.toByteArray();

		String absoluteWebPath = getServletContext().getRealPath("/");
		String serverPath = "/certScan/" + pk + "." + getExtensionPath(filePart);
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

	private RequestDispatcher sendBackInputs(HttpServletRequest request, User user) {
		request.setAttribute("firstname", user.getUR_FirstName());
		request.setAttribute("email", user.getUR_Email());
		request.setAttribute("lastname", user.getUR_LastName());
		request.setAttribute("address", user.getUR_Address());
		request.setAttribute("suburb", user.getUR_Suburb());
		if(user.getUR_Postcode() != null && user.getUR_Postcode() != 0) request.setAttribute("postcode", user.getUR_Postcode());
		request.setAttribute("country", user.getUR_Country());
		request.setAttribute("state", user.getUR_State());
		return getRequestDispatcher(request, "/applyAsTutor.jsp");
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
		return validInputs;
	}
	
	protected RequestDispatcher getRequestDispatcher(HttpServletRequest request, String url) {
		return request.getRequestDispatcher(url);
	}
}
