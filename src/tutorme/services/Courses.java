package tutorme.services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import tutorme.models.CoursesManager;

@Path("/courses")
public class Courses {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String selectTop(@DefaultValue("date") @QueryParam("orderBy") String orderBy) {
		return CoursesManager.getInstance().getCoursesAsJson(orderBy);
	}

}
