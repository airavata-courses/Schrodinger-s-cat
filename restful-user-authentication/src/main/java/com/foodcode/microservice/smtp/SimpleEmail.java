package com.foodcode.microservice.smtp;

import java.util.Properties;

import javax.mail.Session;

import com.foodcode.microservice.smtp.utility.EmailUtil;

public class SimpleEmail {
	
	public static void main(String[] args) {
		
	    System.out.println("SimpleEmail Start");
		
	    String smtpHostServer = "smtp.foodcode.com";
	    String emailID = "nawazkh@iu.edu";
	    
	    Properties props = System.getProperties();

	    props.put("mail.smtp.host", smtpHostServer);

	    Session session = Session.getInstance(props, null);
	    
	    EmailUtil.sendEmail(session, emailID,"SimpleEmail Testing Subject", "SimpleEmail Testing Body");
	}

}

