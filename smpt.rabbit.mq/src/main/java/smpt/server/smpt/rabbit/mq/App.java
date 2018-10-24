package smpt.server.smpt.rabbit.mq;

import java.util.Properties;

import javax.mail.Session;

import smpt.server.smpt.rabbit.mq.utility.EmailUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	 System.out.println("SimpleEmail Start");
 		
 	    String smtpHostServer = "smtp.foodcode.com";
 	    String emailID = "nawazkh@iu.edu";
 	    
 	    Properties props = System.getProperties();

 	    props.put("mail.smtp.host", smtpHostServer);

 	    Session session = Session.getInstance(props, null);
 	    
 	    EmailUtil.sendEmail(session, emailID,"SimpleEmail Testing Subject", "SimpleEmail Testing Body");
    }
}
