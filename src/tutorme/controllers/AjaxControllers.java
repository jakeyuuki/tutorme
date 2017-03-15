package tutorme.controllers;

import org.glassfish.jersey.server.ResourceConfig;

public class AjaxControllers extends ResourceConfig{
	public AjaxControllers() {
		register(AdminManagementController.class);
	}
}
