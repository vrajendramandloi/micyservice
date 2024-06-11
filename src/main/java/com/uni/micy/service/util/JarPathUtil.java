package com.uni.micy.service.util;

import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.file.Paths;

@Slf4j
@Component
public class JarPathUtil {
    private String JAR_PATH;
    private static Logger log = LoggerFactory.getLogger(JarPathUtil.class);

    public String getJarPath() {
        return new String(JAR_PATH);
    }

    @PostConstruct
    public void initParentDirPath() {
        String dirtyPath = getClass().getResource("").toString();
        String jarPath = dirtyPath.replaceAll("^.*file:/", "");
        jarPath = jarPath.replaceAll("jar!.*", "jar");
        jarPath = jarPath.replaceAll("%20", " ");
        if (!jarPath.endsWith(".jar")) {
            jarPath = jarPath.replaceAll("/classes/.*", "/classes/");
        }
        JAR_PATH = Paths.get(jarPath).getParent().toString(); //Paths - from java 8
    }
}
