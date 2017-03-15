package tutorme.util;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuthenticator extends Authenticator{
	// mail.darrenleohartbui.com => email: tutorme@darrenleohartbui.com password: Tutorme123
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication ("tutorme@darrenleohartbui.com", "Tutorme123");
    }
}
