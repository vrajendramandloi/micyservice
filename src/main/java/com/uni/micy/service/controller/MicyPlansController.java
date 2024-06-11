package com.uni.micy.service.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uni.micy.service.util.JarPathUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uni.micy.service.model.PlanDetails;
import com.uni.micy.service.util.AppUtils;

import javax.annotation.PostConstruct;

@RestController
@Slf4j
public class MicyPlansController {
	private List<PlanDetails> inrPlanList = new ArrayList<PlanDetails>();
	private List<PlanDetails> usdPlanList = new ArrayList<PlanDetails>();
	private static Logger log = LoggerFactory.getLogger(MicyPlansController.class);

	@Autowired
	private JarPathUtil jarPathUtil;

	@PostConstruct
	public void loadPlans() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String subsJsonFileStr = "";
		try {
			String path = jarPathUtil.getJarPath() + "/config/subscriptions.json";
			subsJsonFileStr = new String(Files.readAllBytes(Paths.get(path)));
			inrPlanList = mapper.readValue(subsJsonFileStr, new TypeReference<List<PlanDetails>>() {});
		} catch (Exception e) {
			log.error("Exception ", e);
		}
		log.info("Loaded Subscriptions plans for INR "+ inrPlanList.size());
	}

	@GetMapping("/activeplans/{country}")
	public synchronized ResponseEntity<List<PlanDetails>> getplan(@PathVariable("country") String countryId) throws IOException {
		String currency = AppUtils.getCurrency(countryId);
		if(currency.equalsIgnoreCase("INR")) {
			return new ResponseEntity<List<PlanDetails>>(inrPlanList, HttpStatus.OK);
		} 
		if(currency.equalsIgnoreCase("USD")) {
			return new ResponseEntity<List<PlanDetails>>(usdPlanList, HttpStatus.OK);
		}
		return new ResponseEntity<List<PlanDetails>>(new ArrayList<>(), HttpStatus.REQUEST_TIMEOUT);
	}
}
