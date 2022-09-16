package com.uni.micy.service.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uni.micy.service.model.UniResponse;
import com.uni.micy.service.model.UserDetails;

@RestController
@RequestMapping("/userdetails")
public class UserIdController {

	@PostMapping("/generateUid")
	public synchronized UniResponse generateUserUid(@RequestBody UserDetails userDetails) {
		try {
			String uid = generateUid(userDetails);
			return new UniResponse("SUCCESS", "uid:" + uid);
		} catch (Exception e) {

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
			if (value != null) {
				value = 0;
			}
			map.put(c, ++value);
		}
		String finalOutput = "";
		int value = 1;
		String keysString = "";
		for (Map.Entry<Character, Integer> entries : map.entrySet()) {
			keysString += entries.getKey().toString();
			value += entries.getValue();
		}
		value = (value * 13) + 786;
		finalOutput = keysString.substring(0, 2) + value;
		return ud.getMobilenum()+finalOutput.toUpperCase();
	}
}
