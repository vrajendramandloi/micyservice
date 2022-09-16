package com.uni.micy.service.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uni.micy.service.model.UniResponse;
import com.uni.micy.service.model.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/userdetails")
public class UserIdController {
	public static Logger logger = LoggerFactory.getLogger(UserIdController.class);
	
	@PostMapping("/generateUid")
	public synchronized UniResponse generateUserUid(@RequestBody UserDetails userDetails) {
		try {
			logger.info("userDetails object created "+ userDetails.toString());
			String uid = generateUid(userDetails);
			return new UniResponse("SUCCESS", "uid:" + uid);
		} catch (Exception e) {
			logger.error("Exception while Creating UID ", e);
		}
		return new UniResponse("FAILURE", null);
	}

	private synchronized String generateUid(UserDetails ud) {
		String str = (ud.getFullname().replaceAll(" ", "").toLowerCase() + ud.getMobilenum());
		int hash = str.hashCode();
		char[] carr = (str + hash).toCharArray();
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (char c : carr) {
			Integer value = map.get(c);
			if (value == null) {
				value = 0;
			}
			map.put(c, ++value);
		}
		String keysString = "";
		for (Map.Entry<Character, Integer> entries : map.entrySet()) {
			keysString += Character.isLetterOrDigit(entries.getKey()) ? entries.getKey().toString() : entries.getValue();
		}
		keysString = shuffleString(keysString);
		return ud.getMobilenum()+keysString.toUpperCase().substring(0,6);
	}
	
	public static String shuffleString(String string) {
	  List<String> letters = Arrays.asList(string.split(""));
	  Collections.shuffle(letters);
	  String shuffled = "";
	  for (String letter : letters) {
	    shuffled += letter;
	  }
	  return shuffled;
	}
}
