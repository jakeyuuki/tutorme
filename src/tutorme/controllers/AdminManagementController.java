package tutorme.controllers;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tutorme.models.Certification;
import tutorme.models.Tutor;
import tutorme.models.Tutor.TutorSchema;
import tutorme.models.TutorsManager;
import tutorme.models.User;
import tutorme.models.UsersManager;
import tutorme.models.core.BusinessObjectFactory;
import tutorme.util.MD5Hashing;

@Path("/admin")
public class AdminManagementController {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String itWorks() {
		return "Get is OK";
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/userValues")
	public String getUsers(@FormParam("page") int page, @FormParam("rows") int rows) throws JSONException {
		int count = new BusinessObjectFactory().count(User.class);

		int total = (int) Math.ceil((double) count / (double) rows);
		if (page > total)
			page = total;
		JSONArray list = UsersManager.getUsersAsJson((page - 1) * rows, rows);

		JSONObject result = new JSONObject();
		result.put("page", page);
		result.put("total", total);
		result.put("records", count);
		result.put("values", list);

		return result.toString();
	}
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/pendingApplications")
	public String getPendingTutorUsers(@FormParam("page") int page, @FormParam("rows") int rows) throws JSONException {
		int count = new BusinessObjectFactory().count("*", Tutor.class, TutorSchema.TR_IsApproved + "=1");

		int total = (int) Math.ceil((double) count / (double) rows);
		if (page > total)
			page = total;
		
		JSONArray list = TutorsManager.getPendingTutorsAsJson((page - 1) * rows, rows);

		JSONObject result = new JSONObject();
		result.put("page", page);
		result.put("total", total);
		result.put("records", count);
		result.put("values", list);

		return result.toString();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/userEdit")
	public String editUser(@FormParam("oper") String oper, @FormParam("id") String ur_pk,
			@FormParam("ur_firstname") String ur_firstname, @FormParam("ur_lastname") String ur_lastname,
			@FormParam("ur_email") String ur_email, @FormParam("ur_activated") boolean ur_activated, @FormParam("ur_isactive") boolean ur_isactive,
			@FormParam("ur_isadmin") boolean ur_isadmin, @FormParam("ur_istutor") boolean ur_istutor,
			@FormParam("ur_phonenumber") String ur_phonenumber, @FormParam("ur_address") String ur_address,
			@FormParam("ur_suburb") String ur_suburb, @FormParam("ur_state") String ur_state,
			@FormParam("ur_postcode") Integer ur_postcode, @FormParam("ur_country") String ur_country)
					throws JSONException {
		JSONObject result = new JSONObject();
		try {
			if (oper.equals("edit")) {
				BusinessObjectFactory factory = new BusinessObjectFactory();
				User user = factory.loadByPK(ur_pk, User.class);
				if (user != null) {
					user.setUR_FirstName(ur_firstname);
					user.setUR_LastName(ur_lastname);
					user.setUR_Activated(ur_activated);
					user.setUR_IsActive(ur_isactive);
					user.setUR_IsAdmin(ur_isadmin);
					user.setUR_IsTutor(ur_istutor);
					user.setUR_PhoneNumber(ur_phonenumber);
					user.setUR_Address(ur_address);
					user.setUR_Suburb(ur_suburb);
					user.setUR_State(ur_state);
					user.setUR_Postcode(ur_postcode);
					user.setUR_Country(ur_country);
					factory.save();
					result.put("success", true);
					result.put("id", ur_pk);
				} else {
					result.put("success", false);
					result.put("message", String.format("invalid ur_pk: [%1$s]", ur_pk));
				}
			} else {
				result.put("success", false);
				result.put("message", String.format("invalid oper: [%1$s]", oper));
			}
			//System.out.println(result.toString());
			return result.toString();
		} catch (Exception ex) {
			result.put("success", false);
			result.put("message", ex.getLocalizedMessage());
			ex.printStackTrace();
			return result.toString();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/changePassword")
	public String changePassword(@FormParam("id") String ur_pk, @FormParam("password") String password) throws JSONException {
		JSONObject result = new JSONObject();
		try {
			BusinessObjectFactory factory = new BusinessObjectFactory();
			User user = factory.loadByPK(ur_pk, User.class);
			if (user != null) {
				user.setUR_Password(MD5Hashing.getMD5Hash(password));
				factory.save();
				result.put("success", true);
				result.put("message", "Successful change password for " + user.getUR_Email());
			} else {
				result.put("success", false);
				result.put("message", String.format("invalid ur_pk: [%1$s]", ur_pk));
			}
			//System.out.println(result.toString());
			return result.toString();
		} catch (Exception ex) {
			result.put("success", false);
			result.put("message", ex.getLocalizedMessage());
			ex.printStackTrace();
			return result.toString();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/approveApplication")
	public String approveApplication(@FormParam("id") String tr_pk, @FormParam("ur_pk") String ur_pk) throws JSONException {
		JSONObject result = new JSONObject();
		try {
			BusinessObjectFactory factory = new BusinessObjectFactory();
			Tutor tutor = factory.loadByPK(tr_pk, Tutor.class);
			User user = factory.loadByPK(ur_pk, User.class);
			if (tutor != null){
				tutor.setTR_IsApproved(true);
				user.setUR_IsTutor(true);
				factory.save();
				result.put("success", true);
				result.put("message", "Tutor successfully approved!");
				//TODO: Send email "Tutor status approved"

			} else {
				result.put("success", false);
				result.put("message", String.format("invalid ur_pk: [%1$s]", tr_pk));
			}
			//System.out.println(result.toString());
			return result.toString();
		} catch (Exception ex) {
			result.put("success", false);
			result.put("message", ex.getLocalizedMessage());
			ex.printStackTrace();
			return result.toString();
		}
		
		}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/denyApplication")
	public String denyApplication(@FormParam("id") String tr_pk) throws JSONException {
		JSONObject result = new JSONObject();
		try {
			BusinessObjectFactory factory = new BusinessObjectFactory();
			//tutor delete method (factory?)
			Tutor tutor = factory.loadByPK(tr_pk, Tutor.class);
			if (tutor != null){
				List<Certification> certifications = factory.load(Certification.getQueryFindCertificationsByTutorPK(tr_pk), Certification.class);
				for (Certification certification : certifications) {
					certification.delete();
				}
				tutor.delete();
				factory.save();
				result.put("success", true);
				result.put("message", "Tutor successfully denied!");
				//TODO: Send email "Tutor status denied"

			} else {
				result.put("success", false);
				result.put("message", String.format("invalid pk: [%1$s]", tr_pk));
			}
			//System.out.println(result.toString());
			return result.toString();
		} catch (Exception ex) {
			result.put("success", false);
			result.put("message", ex.getLocalizedMessage());
			ex.printStackTrace();
			return result.toString();
		}
		
		}
	
}
