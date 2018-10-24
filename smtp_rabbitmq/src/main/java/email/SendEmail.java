package email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	String from;
	String host;
	Properties properties;
	public SendEmail() {
		from = "noreply@foodcode.com";
		host = "localhost";
		properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
	}
	public void sendEmailToReceipient(String to) {
	    // Setup mail server


	    // Get the default Session object.
	    Session session = Session.getDefaultInstance(properties);

	    try {
	       // Create a default MimeMessage object.
	       MimeMessage message = new MimeMessage(session);

	       // Set From: header field of the header.
	       message.setFrom(new InternetAddress(from));

	       // Set To: header field of the header.
	       message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	       // Set Subject: header field
	       message.setSubject("FoodCode.com -- Account created!");

	       // Now set the actual message
	       message.setText("Hello User with the email : "+to+".\n Your account has been created at foodcode.com.\n\n Regards,\n Foodcode.com");

	       // Send message
	       Transport.send(message);
	       System.out.println("Message sent successfully....");
	    } catch (MessagingException mex) {
	       mex.printStackTrace();
	    }
	}

}
