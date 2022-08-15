package com.uni.micy.service.controller;

import java.time.Instant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {
	@GetMapping("/currentTime")
	public String getCurrentTime() {
		return "MICY: "+Instant.now().toString();
	}
	
}
