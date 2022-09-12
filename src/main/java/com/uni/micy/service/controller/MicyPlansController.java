package com.uni.micy.service.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uni.micy.service.model.PlanDetails;
import com.uni.micy.service.util.AppUtils;

@RestController
public class MicyPlansController {
	List<PlanDetails> inrPlanList = new ArrayList<PlanDetails>();
	List<PlanDetails> usdPlanList = new ArrayList<PlanDetails>();
	
	public MicyPlansController() {
		inrPlanList = loadPlansforINR();
		usdPlanList = loadPlansforUSD();
	}
	
	
	@RequestMapping(value="/activeplans/{country}",  method = RequestMethod.GET)
	public synchronized ResponseEntity<List<PlanDetails>> getplan(@PathVariable("country") String countryId) {
		List<PlanDetails> pdList = new ArrayList<PlanDetails>();
		if(countryId==null && countryId.length() != 0) {
			return new ResponseEntity<List<PlanDetails>>(pdList, HttpStatus.OK);
		}
		String currency = AppUtils.getCurrency(countryId);
		if(currency.equalsIgnoreCase("INR")) {
			return new ResponseEntity<List<PlanDetails>>(inrPlanList, HttpStatus.OK);
		} 
		if(currency.equalsIgnoreCase("USD")) {
			return new ResponseEntity<List<PlanDetails>>(usdPlanList, HttpStatus.OK);
		}
		return new ResponseEntity<List<PlanDetails>>(pdList, HttpStatus.REQUEST_TIMEOUT);
	}
	
	List<PlanDetails> loadPlansforINR() {
		List<PlanDetails> planList = new ArrayList<PlanDetails>();
		
		PlanDetails plan1 = new PlanDetails(101, 360, "Annual-A", 151, 199, "INR", "ACTIVE", "IND", "APP_ACTIVATE");
		plan1.setPlanDesc("(1 Year Plan) Enable all Utility functions of MICY App. U can use My Diary,Notes, Journal, Organizing List, Calendar, Finance etc..");
		planList.add(plan1);
		PlanDetails plan2 = new PlanDetails(103, 720, "Annual-B", 256, 499, "INR", "ACTIVE", "IND", "APP_ACTIVATE");
		plan2.setPlanDesc("(2 Year Plan) Enable all Utility functions of MICY App. U can use My Diary,Notes, Journal, Organizing List, Calendar, Finance etc..");
		planList.add(plan2);
		PlanDetails plan3 = new PlanDetails(103, 1821, "Annual-E", 996, 1499, "INR", "ACTIVE", "IND", "APP_ACTIVATE");
		plan3.setPlanDesc("(5 Year Plan) Enable all Utility functions of MICY App. U can use My Diary,Notes, Journal, Organizing List, Calendar, Finance etc..");
		planList.add(plan3);
		return planList;
	}
	
	List<PlanDetails> loadPlansforUSD() {
		List<PlanDetails> planList = new ArrayList<PlanDetails>();
		
		PlanDetails plan1 = new PlanDetails(105, 360, "Annual-A", 2.52, 3.50, "USD", "ACTIVE", "USA", "APP_ACTIVATE");
		plan1.setPlanDesc("(1 Year Plan) Enable all Utility functions of MICY App. U can use My Diary,Notes, Journal, Organizing List, Calendar, Finance etc..");
		planList.add(plan1);
		PlanDetails plan2 = new PlanDetails(106, 720, "Annual-B", 4.41, 6.50, "USD", "ACTIVE", "USA", "APP_ACTIVATE");
		plan2.setPlanDesc("(2 Year Plan) Enable all Utility functions of MICY App. U can use My Diary,Notes, Journal, Organizing List, Calendar, Finance etc..");
		planList.add(plan2);
		PlanDetails plan3 = new PlanDetails(107, 1821, "Annual-E", 19.92, 25.25, "USD", "ACTIVE", "USA", "APP_ACTIVATE");
		plan3.setPlanDesc("(5 Year Plan) Enable all Utility functions of MICY App. U can use My Diary,Notes, Journal, Organizing List, Calendar, Finance etc..");
		planList.add(plan3);
		return planList;
	}
}
