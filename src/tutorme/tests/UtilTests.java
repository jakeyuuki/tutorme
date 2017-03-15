package tutorme.tests;

import tutorme.util.Mailer;

public class UtilTests extends TestCaseBase{
	// mail.darrenleohartbui.com => email: tutorme@darrenleohartbui.com password: Tutorme123 to check testEmail.
	public void testSendEmail() {
		Mailer.setPropsAndSendEmail(Mailer.email, "Test Email");
	}
}
