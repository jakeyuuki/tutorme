package tutorme.services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import tutorme.models.TutorsManager;

@Path("/tutors")
public class Tutors {
	
	// This method is called if TEXT_PLAIN is request
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String selectTop(@DefaultValue("name") @FormParam("orderBy") String orderBy, @DefaultValue("") @FormParam("name") String name) {
		return TutorsManager.getInstance().getCoursesAsJson(orderBy, name);
	}
	
	@GET
	@Path("pending")
	@Produces(MediaType.APPLICATION_JSON)
	public String selectTopPending() {
		return TutorsManager.getPendingTutorsAsJsonString(0);
	}

}
