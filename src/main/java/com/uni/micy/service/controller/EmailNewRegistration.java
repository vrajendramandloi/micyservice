package com.uni.micy.service.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.*;

import javax.annotation.PostConstruct;

import com.uni.micy.service.queue.LinkedBlockingQueue;
import com.uni.micy.service.util.JarPathUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uni.micy.service.model.UniResponse;
import com.uni.micy.service.model.UserDetails;

@RestController
@PropertySource("classpath:mailInfo.properties")
public class EmailNewRegistration {
	private static Logger log = LoggerFactory.getLogger(UserIdController.class);
	@Autowired
	private LinkedBlockingQueue linkedBlockingQueue;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private Properties mailProperties;
	@Autowired
	private JarPathUtil jarPathUtil;

	public void setMailProperties(Properties mailProperties) {
		this.mailProperties = mailProperties;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
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
		//this.mailSender = sender;
		String emailBody = "";
		try {
			String emailBodyFilePath = jarPathUtil.getJarPath() + "/emailBody.html";
			emailBody = new String(Files.readAllBytes(Paths.get(emailBodyFilePath)));
		} catch(Exception e) {
			log.error("Exception while reading Mail Body Html File ", e);
		}
		ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		final EmailSenderThread emailSenderThread = new EmailSenderThread(
				linkedBlockingQueue.getBlockingQueue(), mailSender, emailBody);
		executorService.scheduleWithFixedDelay(emailSenderThread, 0,30, TimeUnit.SECONDS);
	}
	
	
	@PostMapping("/registerUser")
	public synchronized UniResponse sendRegistrationSuccessMail(@RequestBody UserDetails userDetails) {
		linkedBlockingQueue.getBlockingQueue().add(userDetails);
		return new UniResponse("SUCCESS", null);
	}
}

@Slf4j
class EmailSenderThread implements Runnable {
	private static Logger log = LoggerFactory.getLogger(EmailSenderThread.class);
	private BlockingQueue<UserDetails> blockingQueue;
	private JavaMailSender mailSender;
	private String mailBody;

	public EmailSenderThread(BlockingQueue<UserDetails> blockingQueue, JavaMailSender mailSender, String mailBody) {
		this.blockingQueue = blockingQueue;
		this.mailSender = mailSender;
		this.mailBody = mailBody;
	}

	@Override
	public void run() {
		UserDetails ud = blockingQueue.remove();
		while(ud!=null) {
			sendEmail(ud);
		}
	}

	public void sendEmail(UserDetails ud) {
		log.info("userDetails object Obtained for Registration : "+ ud.toString());
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("admin@vrajme.co.in");
		msg.setTo(ud.getEmailid());
		msg.setSubject("IMP: Welcome to MIICY");
		msg.setText(mailBody);
		mailSender.send(msg);
	}
}
