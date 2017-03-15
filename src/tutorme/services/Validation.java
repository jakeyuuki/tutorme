package tutorme.services;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;

import tutorme.models.User;
import tutorme.models.core.BusinessObjectFactory;

@Path("/validation")
public class Validation {
	@Path("/email")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String selectTop(@DefaultValue("") @FormParam("email") String email) throws JSONException {
		boolean isValid = new BusinessObjectFactory().loadTop1(User.getQueryFindEmail(email), User.class) == null;
		return new JSONObject().put("valid", isValid).toString();
	}
}
