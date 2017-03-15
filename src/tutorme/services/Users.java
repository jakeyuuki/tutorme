package tutorme.services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import tutorme.models.UsersManager;

@Path("/users")
public class Users {
	
	// This method is called if TEXT_PLAIN is request
	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public String selectTop() {
		  return UsersManager.getUsersAsJson(0);
	  }
	  
	  @GET
	  @Path("{id}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public String selectTopOne(@PathParam("id") String id) {
		  return UsersManager.getSingleUserAsJson(id);
	  }

}
