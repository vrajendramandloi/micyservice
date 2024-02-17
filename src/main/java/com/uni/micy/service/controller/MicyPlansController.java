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
		
		PlanDetails plan1 = new PlanDetails(101, 365, "Free", 0, 0, "INR", "ACTIVE", "IND", "APP_ACTIVATE");
		plan1.setPlanDesc("All Free app functions are available for the user. All App functions can be used offline on device. Ad's will be available when online. "
				+ "Following are app main utils: \n* Manage Asset & Family. \n* Manage Portfolio's \n* Notes \n* Plan List \n* Calendar \n* Self Tracker ");
		planList.add(plan1);
		PlanDetails plan2 = new PlanDetails(101, 1111, "Prime-Family", 199, 199, "INR", "ACTIVE", "IND", "APP_ACTIVATE");
		plan2.setPlanDesc("All Free App Features are available only Ad's are Remove Ad's. "
				+ "Following are app main utils: \n* Manage Asset & Family. \n* Manage Portfolio's \n* Notes \n* Plan List \n* Calendar \n* Self Tracker");
		planList.add(plan2);
		PlanDetails plan3 = new PlanDetails(101, 1111, "Prime-Influencer", 996, 996, "INR", "ACTIVE", "IND", "APP_ACTIVATE");
		plan3.setPlanDesc("Addon's for Influencers are available in this plan. "
				+ "Following are app main utils: \n* Schedule Campaign \n* Manage Products \n* Manage Associates \n* Finance");
		planList.add(plan3);
		return planList;
	}
	
	List<PlanDetails> loadPlansforUSD() {
		List<PlanDetails> planList = new ArrayList<PlanDetails>();
		
		PlanDetails plan1 = new PlanDetails(105, 1111, "Prime-A", 3.61, 3.61, "USD", "ACTIVE", "USA", "APP_ACTIVATE");
		plan1.setPlanDesc("Becoming Prime enables all app functions for the user. All App functions can be used offline on device. Following are app main utils: \n* My Diary \n* Notes \n* Plan List \n* Calendar \n* Self Tracker + Finance");
		planList.add(plan1);
		return planList;
	}
}
