package com.uni.micy.service.util;

public class AppUtils {
	public static boolean isEmpty(String str) {
		if(str != null && str.equalsIgnoreCase("null")) return false;
		return (str != null && str.trim().length() != 0);
	}
	public static synchronized String getCurrency(String country) {
		String currency = "INR";
		if(!country.equalsIgnoreCase("INDIA")) {
			currency = "USD";
		}
		return currency;
	}
}
