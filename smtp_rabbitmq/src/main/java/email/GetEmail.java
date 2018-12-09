package email;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.*;

public class GetEmail {
	public GetEmail() {
		// TODO Auto-generated constructor stub
	}
	private static final String EXCHANGE_NAME = "mail_list";
	private static final String ROUTING_KEY = "black";

	public void sendEmailToUser(String emailId) {
		try {
			ConnectionFactory factory = new ConnectionFactory();
//			factory.setHost("rabbitserver");//host name
			factory.setHost("service-rabbitserver");//host name
			//factory.setPort(5672);
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
}
