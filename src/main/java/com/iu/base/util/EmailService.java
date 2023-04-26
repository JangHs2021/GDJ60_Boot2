package com.iu.base.util;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

	@Value("${spring.mail.username}")
	private String sender;
	
	@Autowired
	private JavaMailSender emailSender;
	
	public void sendEmail(String to, String subject, String text) throws Exception {
		MimeMessage miMessage = emailSender.createMimeMessage();
		miMessage.setFrom(sender);
		miMessage.addRecipient(RecipientType.TO, new InternetAddress(to));
		miMessage.setSubject(subject);
		miMessage.setText(text);
		emailSender.send(miMessage);
		
		// HTML 태그를 무시하고 그래도 text로 전송
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(sender);
//        message.setTo(to); 
//        message.setSubject(subject); 
//        message.setText(text);
//        emailSender.send(message);
    }   
}
