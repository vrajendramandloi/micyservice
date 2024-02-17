package com.uni.micy.service.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uni.micy.service.model.UniResponse;
import com.uni.micy.service.model.UserDetails;

@RestController
@PropertySource("classpath:mailInfo.properties")
public class EmailNewRegistration {
	public static Logger logger = LoggerFactory.getLogger(UserIdController.class);
	private JavaMailSender mailSender;
	@Autowired
	private Properties mailProperties;
	public void setMailProperties(Properties mailProperties) {
		this.mailProperties = mailProperties;
	}
	
	@PostConstruct
	public void init() throws FileNotFoundException, IOException {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost(mailProperties.getProperty("spring.mail.host"));
		int port = Integer.parseInt(mailProperties.getProperty("spring.mail.port"));
		sender.setPort(port);
		sender.setUsername(mailProperties.getProperty("spring.mail.username"));
		sender.setPassword(mailProperties.getProperty("spring.mail.password"));
		sender.setProtocol(mailProperties.getProperty("mail.transport.protocol"));
		sender.setJavaMailProperties(mailProperties);
		this.mailSender = sender;
	}
	
	
	@PostMapping("/registerUser")
	public synchronized UniResponse sendRegistrationSuccessMail(@RequestBody UserDetails userDetails) {
		try {
			logger.info("userDetails object Obtained for Registration : "+ userDetails.toString());
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setTo("vrajendra.singh.mandloi@gmail.com");
			msg.setSubject("Welcome to Micy");
			msg.setText("Hello World \n Spring Boot Email");

			mailSender.send(msg);
			return new UniResponse("SUCCESS", "Success ");
		} catch (Exception e) {
			logger.error("Exception while Creating UID ", e);
		}
		return new UniResponse("FAILURE", null);
	}
	
}
