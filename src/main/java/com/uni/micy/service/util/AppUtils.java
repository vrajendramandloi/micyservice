package com.uni.micy.service.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class AppUtils {
    public static boolean isEmpty(String str) {
        if (str != null && str.equalsIgnoreCase("null")) return false;
        return (str != null && str.trim().length() != 0);
    }

    public static synchronized String getCurrency(String country) {
        String currency = "INR";
        if (!"INDIA".equalsIgnoreCase(country)) {
            currency = "USD";
        }
        return currency;
    }

    public static File addFilesToZip(String source, String fileName, List<Path> filesPathList) throws IOException {
		String zipPath = source+"/"+fileName;
		try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipPath))) {
			for (Path filePath : filesPathList) {
				File fileToZip = filePath.toFile();
				zipOut.putNextEntry(new ZipEntry(fileToZip.getName()));
				Files.copy(fileToZip.toPath(), zipOut);
			}
		}
		return new File(zipPath);
    }
}
