package com.uni.micy.service;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class MailConfigBeans {
	public static Logger logger = LoggerFactory.getLogger(MailConfigBeans.class);
	
	@Bean
	public Properties mailProperties() {
	    PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
	    propertiesFactoryBean.setLocation(new ClassPathResource("/mailInfo.properties"));
	    Properties properties = null;
	    try {
	        propertiesFactoryBean.afterPropertiesSet();
	        properties = propertiesFactoryBean.getObject();

	    } catch (IOException e) {
	    	logger.warn("Cannot load properties file.");
	    }
	    return properties;
	}
}
