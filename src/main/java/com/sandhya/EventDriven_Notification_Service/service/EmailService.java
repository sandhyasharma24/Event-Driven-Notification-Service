package com.sandhya.EventDriven_Notification_Service.service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	private JavaMailSender mailSender;
	 public EmailService(JavaMailSender mailSender) {
	        this.mailSender = mailSender;
	    }	
	
	
	public void sendEmail(String toEMail, String body,String subject ) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(toEMail);
		message.setSubject(subject);
		message.setText(body);
		mailSender.send(message);
	}

}
