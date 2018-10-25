package email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import email.utility.EmailUtil;

public class SendEmailGmail {
	String fromEmail;
	String password;
	Properties props;
	public SendEmailGmail() {
		fromEmail = "foodcode123ad@gmail.com"; //requires valid gmail id
		password = "SimplePassword@123"; 
		props = new Properties(); 
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
		props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
		props.put("mail.smtp.port", "465"); //SMTP Port
	}
	/**
	   Outgoing Mail (SMTP) Server
	   requires TLS or SSL: smtp.gmail.com (use authentication)
	   Use Authentication: Yes
	   Port for SSL: 465
	 */
	public void sendEmailFromGmailAccount(String toEmail) {
		// correct password for gmail id
		//final String toEmail = "myemail@yahoo.com"; // can be any email id 
		System.out.println("SSLEmail Start");
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		
		Session session = Session.getDefaultInstance(props, auth);
		System.out.println("Session created");
	        EmailUtil.sendEmail(session, toEmail,"SSLEmail Testing Subject", "SSLEmail Testing Body");

	}

}


