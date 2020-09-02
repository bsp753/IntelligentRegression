package com.insta.application.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.insta.application.data.ExternalEmailPayloadData;
import com.insta.application.data.MyDataSource;
import com.insta.application.data.PaymentEmailData;
import com.insta.application.model.DBFile;
import com.insta.application.model.PaymentInfo;

@Service
public class ExternalEmailService 
{
	@Autowired
	private JavaMailSender mailSender;
	
	public String sendEmail(ExternalEmailPayloadData mailPayLoad) throws Exception{

		System.out.println(mailPayLoad.getEmail()+mailPayLoad.getHost());
		System.out.println(mailPayLoad.getPhone()+"-"+mailPayLoad.getSubject());
		String SMTP_HOST_NAME = "smtpout.secureserver.net"; //smtp URL
		int SMTP_HOST_PORT = 465; //port number
		String SMTP_AUTH_USER = "vamsi@sabkainsurance.in"; //email_id of sender
		String SMTP_AUTH_PWD = "Wipro!123"; //password of sender email_id
		
		try {
			Properties props = new Properties();
			props.put("mail.transport.protocol", "smtps");
			props.put("mail.smtps.host", SMTP_HOST_NAME);
			props.put("mail.smtps.auth", "true");

			Session mailSession = Session.getDefaultInstance(props);
			mailSession.setDebug(true);
			Transport transport = mailSession.getTransport();
			MimeMessage message = new MimeMessage(mailSession);
			 MimeMessageHelper helper = new MimeMessageHelper(message, true);
				String toEmail = getemail(mailPayLoad.getHost());
					helper.setSubject("New Enquiry - "+mailPayLoad.getHost());
			        helper.setText("<html>\r\n" + 
			        		"<p>New Enquiry</p>\r\n" + 
			        		"<p>&nbsp;</p>\r\n" + 
			        		"<p> Sender - "
			        		+ mailPayLoad.getEmail() + "\n\r"
			        		+ "<p> Phone - " + mailPayLoad.getPhone()+ "\n\r"
			        		+ "<p> Subject - " + mailPayLoad.getSubject()+ "\n\r"
			        		+ "<p> Name - " + mailPayLoad.getName()+ "\n\r"
			        		
			        		+ "<p> Message - " + mailPayLoad.getMessage()+ "\n\r</p>\r\n" + 
			        		"<p>&nbsp;</p>\r\n" + 
			        		"<p>Thanks,</p>\r\n" + 
			        		"<p>Vamsi</p>\r\n" + 
			        		"</html>", true);
			        helper.setTo(toEmail);
			        helper.setFrom("admin@sabkainsurance.in");
			        
					transport.connect(SMTP_HOST_NAME, SMTP_HOST_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD);
					transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
					transport.close();
					return "sucess";

		} 
		catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}


	}

	private String getemail(String host) 
	{
		String toEmail = "";
		if(host.toLowerCase().contains("utkrant"))
		{
			toEmail = "sridhar@utkrant.com";
		}
		else if(host.toLowerCase().contains("shreshthaahar"))
		{
			toEmail = "tejendra@shreshthaahar.com";
		}
		return toEmail;
	}
	
	}
