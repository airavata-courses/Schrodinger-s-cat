package com.foodcode.microservices.smtp.service;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.joda.time.LocalDate;

import com.rabbitmq.client.*;

import email.GetEmail;
import email.SendEmail;
import email.SendEmailGmail;



public class App {

	private static final Logger logger = Logger.getLogger(App.class);
	private static final String EXCHANGE_NAME = "mail_list";
	private static final String ROUTING_KEY = "black";

	public static void main(String[] args) throws IOException {
		System.out.println(getLocalCurrentDate());
		GetEmail getEmail = new GetEmail();
		try {
			ListenExchange();
			//getEmail.sendEmailToUser("nawazkh@iu.edu");
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
	}

	private static String getLocalCurrentDate() {
		if (logger.isDebugEnabled()) {
			logger.debug("getLocalCurrentDate() is executed!");
		}
		LocalDate date = new LocalDate();
		return date.toString();
	}
	public static void ListenExchange() throws TimeoutException, IOException {
		try {
			ConnectionFactory factory = new ConnectionFactory();
//			factory.setHost("rabbitserver");//host name
			factory.setHost("service-rabbitserver");//host name
			//factory.setPort(5672);//TODO: change it to other when running on docker
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();

			channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
			String queueName = channel.queueDeclare().getQueue();

			channel.queueBind(queueName, EXCHANGE_NAME, ROUTING_KEY);
			System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

			Consumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope,
						AMQP.BasicProperties properties, byte[] body) throws IOException {
					String emailId = new String(body, "UTF-8");
//					
					SendEmail sendEmail = new SendEmail();
//					// call send email here.
//					sendEmail.sendEmailToReceipient(emailId);
					SendEmailGmail emailGmail = new SendEmailGmail();
					emailGmail.sendEmailFromGmailAccount(emailId);
					System.out.println(" [x] routing key:  '" + envelope.getRoutingKey() + "':' email sent to " + emailId + "'");
				}
			};
			channel.basicConsume(queueName, false, consumer);
		}catch (TimeoutException e) {
			e.printStackTrace();
			System.exit(1);
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
