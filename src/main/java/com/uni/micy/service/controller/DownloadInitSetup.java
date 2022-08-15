package com.uni.micy.service.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DownloadInitSetup {
	
	@GetMapping("/initsetup/download")
    public ResponseEntity<?> downloadFile() {
        Resource resource = null;
        try {
        	resource = new ClassPathResource("micyInitSetup.zip");
        } catch (Exception e) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }
         
        if (resource == null) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);       
    }
}
