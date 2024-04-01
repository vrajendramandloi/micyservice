package com.uni.micy.service.controller;

import com.uni.micy.service.util.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@Slf4j
public class DownloadInitSetup {
    private File jsonZipGeneratedFile = null;
    private File soundZipGeneratedFile = null;
    private Logger log = LoggerFactory.getLogger(DownloadInitSetup.class);
    private String jarPath;

    public String getParentDirectoryFromJar() {
        String dirtyPath = getClass().getResource("").toString();
        String jarPath = dirtyPath.replaceAll("^.*file:/", "");
        jarPath = jarPath.replaceAll("jar!.*", "jar");
        jarPath = jarPath.replaceAll("%20", " ");
        if (!jarPath.endsWith(".jar")) {
            jarPath = jarPath.replaceAll("/classes/.*", "/classes/");
        }
        String directoryPath = Paths.get(jarPath).getParent().toString(); //Paths - from java 8
        return directoryPath;
    }

    @PostConstruct
    public void init() {
        this.jarPath = getParentDirectoryFromJar();
        jsonZipGeneratedFile = this.readFromPathAndCreateZip(jarPath+"/json", "jsonConfig.zip");
        soundZipGeneratedFile = this.readFromPathAndCreateZip(jarPath+"/sound", "soundConfig.zip");
    }

    public File readFromPathAndCreateZip(String dirPath, String zipName) {
        File file = null;
        try {
            Path path = Paths.get(dirPath);
            try (Stream<Path> paths = Files.walk(path)) {
                List<Path> jsonPathList = paths.filter(x -> x.getFileName().toString().endsWith(".json"))
                        .collect(Collectors.toList());
                log.info(jsonPathList.toString());
                file = AppUtils.addFilesToZip(jarPath, zipName, jsonPathList);
                log.info("zipFile created : "+file.getAbsolutePath());
            }
        } catch (Exception e) {
            log.error("Exception while constructing zip of all Json ",e);
        }
        return file;
    }

    @GetMapping("/initsetup/download/configZip")
    public ResponseEntity<?> downloadConfigZip() throws FileNotFoundException {
        if (jsonZipGeneratedFile == null || !jsonZipGeneratedFile.exists()) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }
        Resource resource = new InputStreamResource(new FileInputStream(jsonZipGeneratedFile));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + jsonZipGeneratedFile.getName() + "\"")
                .body(resource);
    }

    @GetMapping("/initsetup/download/soundZip")
    public ResponseEntity<?> downloadSoundZip() throws FileNotFoundException {
        if (soundZipGeneratedFile == null || !soundZipGeneratedFile.exists()) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }
        Resource resource = new InputStreamResource(new FileInputStream(soundZipGeneratedFile));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + soundZipGeneratedFile.getName() + "\"")
                .body(resource);
    }
}
