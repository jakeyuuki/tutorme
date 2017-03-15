package tutorme.models;

import tutorme.models.core.BusinessObjectFactory;

public class TutorManager {	
	
	public static Tutor activateUserAsTutor(User user, BusinessObjectFactory factory) {
		Tutor tutor = factory.create(Tutor.class);
		tutor.setMandatoryFields(user.PK, "Summary");
		user.setUR_IsTutor(true);
		return tutor;
	}
}
