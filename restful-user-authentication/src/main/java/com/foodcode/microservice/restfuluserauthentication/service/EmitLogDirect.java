package com.foodcode.microservice.restfuluserauthentication.service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

@Service
public class EmitLogDirect {
	@Autowired
	private Environment env;
	
	private static final String EXCHANGE_NAME = "mail_list";
	private static final String ROUTING_KEY = "black";

	public void sendEmailToUser(String emailId) {
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(env.getProperty("rabbitmq.network.name"));//host name
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();

			channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

			channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, emailId.getBytes("UTF-8"));
			System.out.println(" [x] Sent '" + ROUTING_KEY + "':'" + emailId + "'");

			channel.close();
			connection.close();
		}catch (TimeoutException e) {
			e.printStackTrace();
		}	
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	//	private static String getMessage(String[] strings){
	//		if (strings.length < 2)
	//			return "Hello World!";
	//		return joinStrings(strings, " ", 1);
	//	}
	//
	//	private static String joinStrings(String[] strings, String delimiter, int startIndex) {
	//		int length = strings.length;
	//		if (length == 0 ) return "";
	//		if (length < startIndex ) return "";
	//		StringBuilder words = new StringBuilder(strings[startIndex]);
	//		for (int i = startIndex + 1; i < length; i++) {
	//			words.append(delimiter).append(strings[i]);
	//		}
	//		return words.toString();
	//	}
}
