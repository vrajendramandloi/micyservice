package com.uni.micy.service.controller;

import java.time.Instant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MicyController {
	@GetMapping("/")
	public String getCurrentTime() {
		return "<!DOCTYPE html> <html> <body> <center> <h1>Welcome To M.I.C.Y</h1><p>CURRENT TIME</p> <p>"+Instant.now().toString()+"</p></center></body></html>";
	}
	
}
