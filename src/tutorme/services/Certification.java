package tutorme.services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import tutorme.models.CertificationManager;

@Path("/certifications")
public class Certification {
	
	// This method is called if TEXT_PLAIN is request
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String selectTop() {
		return CertificationManager.getCertsAsJson(0);
	}
	
	 @GET
	  @Path("{id}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public String selectTopOne(@PathParam("id") String id) {
		  return CertificationManager.getSingleCertAsJson(id);
	  }

}
