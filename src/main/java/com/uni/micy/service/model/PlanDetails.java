package com.uni.micy.service.model;

public class PlanDetails {
	int planId;
	int validityDays;
	String validityText;
	String planName;
	double planBuyPrice;
	double planActualPrice;
	String planCurrency;
	String planDesc;
	String planStatus;
	String countryId;
	String planType;
	
	public PlanDetails() {}
	public PlanDetails(int planId, int validityDays, String planName, double planBuyPrice,
			double planActualPrice, String planCurrency, String planStatus, String countryId, String planType) {
		super();
		this.planId = planId;
		this.validityDays = validityDays;
		this.planName = planName;
		this.planBuyPrice = planBuyPrice;
		this.planActualPrice = planActualPrice;
		this.planCurrency = planCurrency;
		this.planStatus = planStatus;
		this.countryId = countryId;
		this.planType = planType;
	}
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}
	public int getValidityDays() {
		return validityDays;
	}
	public void setValidityDays(int validityDays) {
		this.validityDays = validityDays;
	}
	public String getValidityText() {
		return validityText;
	}
	public void setValidityText(String validityText) {
		this.validityText = validityText;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public double getPlanBuyPrice() {
		return planBuyPrice;
	}
	public void setPlanBuyPrice(double planBuyPrice) {
		this.planBuyPrice = planBuyPrice;
	}
	public double getPlanActualPrice() {
		return planActualPrice;
	}
	public void setPlanActualPrice(double planActualPrice) {
		this.planActualPrice = planActualPrice;
	}
	public String getPlanCurrency() {
		return planCurrency;
	}
	public void setPlanCurrency(String planCurrency) {
		this.planCurrency = planCurrency;
	}
	public String getPlanDesc() {
		return planDesc;
	}
	public void setPlanDesc(String planDesc) {
		this.planDesc = planDesc;
	}
	public String getPlanStatus() {
		return planStatus;
	}
	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
	}
}
