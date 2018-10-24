package com.foodcode.microservices.smtp.service;

import java.util.Properties;

import javax.mail.Session;

import org.apache.log4j.Logger;
import org.joda.time.LocalDate;

import com.foodcode.microservices.smtp.service.util.EmailUtil;


public class App {

	private static final Logger logger = Logger.getLogger(App.class);

	public static void main(String[] args) {

		System.out.println(getLocalCurrentDate());
		System.out.println("SimpleEmail Start");

		String smtpHostServer = "smtp.foodcode.com";
		String emailID = "nawazkh@iu.edu";

		Properties props = System.getProperties();

		props.put("mail.smtp.host", smtpHostServer);

		Session session = Session.getInstance(props, null);

		EmailUtil.sendEmail(session, emailID,"SimpleEmail Testing Subject", "SimpleEmail Testing Body");
	}

private static String getLocalCurrentDate() {

	if (logger.isDebugEnabled()) {
		logger.debug("getLocalCurrentDate() is executed!");
	}

	LocalDate date = new LocalDate();
	return date.toString();

}

}
