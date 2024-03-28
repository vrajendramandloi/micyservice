package com.uni.micy.service.controller;

import com.uni.micy.service.util.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@Slf4j
public class DownloadInitSetup {
    private File zipResourceFile = null;
    private Logger log = LoggerFactory.getLogger(DownloadInitSetup.class);

    @PostConstruct
    public void createZipFile() {
        Resource resource = null;
        try {
            String jarPath = getClass()
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI()
                    .getPath();

            log.info("Jar Path : "+ jarPath);
            resource = new ClassPathResource("json/listify.json");
            File resourceFile = resource.getFile();
            String resourceFilePath = resourceFile.getAbsolutePath();
            resourceFilePath = resourceFilePath.replace("listify.json","");
            if(jarPath.contains(".jar")) {
                jarPath = jarPath.substring(0, jarPath.lastIndexOf("/"));
            }
            log.info("Jar Path source : "+ jarPath);
            Path path = Paths.get(resourceFilePath);
            try (Stream<Path> paths = Files.walk(path)) {
                List<Path> jsonPathList = paths.filter(x -> x.getFileName().toString().endsWith(".json"))
                        .collect(Collectors.toList());
                log.info(jsonPathList.toString());
                zipResourceFile = AppUtils.addFilesToZip(jarPath, jsonPathList);
                log.info("zipFile "+zipResourceFile.getAbsolutePath());
            }
        } catch (Exception e) {
            log.error("Exception while constructing zip of all Json ",e);
        }
    }

    @GetMapping("/initsetup/download/generatedZip")
    public ResponseEntity<?> downloadFileZip() {
        if (zipResourceFile == null || !zipResourceFile.exists()) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + zipResourceFile.getName() + "\"")
                .body(zipResourceFile);
    }

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
	
	@GetMapping("/initsetup/diarysound/download")
    public ResponseEntity<?> downloadDiarySoundFile() {
        Resource resource = null;
        try {
        	resource = new ClassPathResource("diarysound.zip");
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
