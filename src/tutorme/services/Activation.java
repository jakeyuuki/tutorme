package tutorme.services;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import tutorme.models.User;
import tutorme.models.UserActivation;
import tutorme.models.core.BusinessObjectFactory;

@Path("/activate")
public class Activation {
	@GET
	public Response activate(@DefaultValue("") @QueryParam("activationCode") String activationCode) throws URISyntaxException {
		URI location;
		BusinessObjectFactory factory = new BusinessObjectFactory();
		UserActivation activation = factory.loadByPK(activationCode, UserActivation.class);
		if(activation != null) {
			User user = factory.loadByPK(activation.getUA_UR_PK(), User.class);
			if(user.getUR_Activated()) {
				location = new URI("/activationActivated.jsp");
			}
			else{
				user.setUR_Activated(true);
				factory.save();
				location = new URI("/activationSuccess.jsp");
			}
		} else {
			location = new URI("/404.jsp");
		}
		return Response.temporaryRedirect(location).build();
	}
}
