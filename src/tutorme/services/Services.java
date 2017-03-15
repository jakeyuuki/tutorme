package tutorme.services;

import org.glassfish.jersey.server.ResourceConfig;

public class Services extends ResourceConfig{
	public Services() {
		packages("tutorme.services");
	}
}
