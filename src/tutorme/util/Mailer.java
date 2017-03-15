package tutorme.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;

public class Mailer {
	// mail.darrenleohartbui.com => email: tutorme@darrenleohartbui.com password: Tutorme123
	public final static String	email		= "tutorme@darrenleohartbui.com";
	//private final static String	password	= "Tutorme123";
	private final static String	subject		= "Tutorme Registration Confirmation Mail";

	public static void setPropsAndSendEmail(String emailRecipient, String emailText) {
		try {

			Properties sessionProperties = new Properties();
			sessionProperties.put("mail.smtp.auth", "true");
			sessionProperties.put("mail.smtp.starttls.enable", "true");
			sessionProperties.put("mail.smtp.host", "email.darrenleohartbui.com");
			sessionProperties.put("mail.smtp.port", "2525");
			//sessionProperties.put("mail.smtp.socketFactory.port","2525");
			//sessionProperties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			
			MailAuthenticator authentificatorForMessage = new MailAuthenticator();
			Session session = Session.getInstance(sessionProperties, authentificatorForMessage);
			Message emailMessage = new MimeMessage(session);
			emailMessage.setFrom(new InternetAddress(email));
			emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailRecipient, false));
			emailMessage.setSubject(subject);
			emailMessage.setSentDate(new Date());
			emailMessage.setText(emailText);
			Transport.send(emailMessage);
			System.out.println("Your email to " + emailRecipient + " has been sent successfully");
		} catch (Exception e) {
			System.out.println("Your email to " + emailRecipient + "  has not been sent: " + e.getMessage());
			e.printStackTrace();
		}
	}
// If Google email
//	sessionProperties.put("mail.smtp.auth", "true");
//	sessionProperties.put("mail.smtp.host", "smtp.gmail.com");
//	sessionProperties.put("mail.smtp.port", 465);
//	sessionProperties.put("mail.smtp.socketFactory.port","465");
//	sessionProperties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
//	MailAuthenticator authentificatorForMessage = new MailAuthenticator();
//	Session session = Session.getInstance(sessionProperties, authentificatorForMessage);
}
