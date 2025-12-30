package com.iispl.jca;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.*;

public class FolderScan {

    public static void main(String[] args) throws Exception {
        String folderPath = "/Home/Downloads"; 

        Map<String, List<File>> hashMap = new HashMap<>();
        
        System.out.println("=======================starting==========================");

        File folder = new File(folderPath);
        if (!folder.isDirectory()) {
            System.out.println("Invalid folder path");
            return;
        }

        for (File file : folder.listFiles()) {
            if (file.isFile()) {
            	
            	System.out.println("=======================Checking==========================");
                String hash = getFileHash(file);

                hashMap.computeIfAbsent(hash, k -> new ArrayList<>()).add(file);
            }
        }

        System.out.println("Duplicate Files Found:");
        for (Map.Entry<String, List<File>> entry : hashMap.entrySet()) {
        	  System.out.println("=======================inside the loop==========================");
            if (entry.getValue().size() > 1) {
            	  System.out.println("=======================If Else loop==========================");
                System.out.println("\nDuplicate group:");
                for (File f : entry.getValue()) {
                    System.out.println(" → " + f.getAbsolutePath());
                }
            }
        }
    }

    private static String getFileHash(File file) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        
        System.out.println("=======================Converting==========================");
        
        try (InputStream is = new FileInputStream(file)) {
            byte[] buffer = new byte[8192];
            int bytesRead;

            while ((bytesRead = is.read(buffer)) != -1) {
                md.update(buffer, 0, bytesRead);
            }
        }

        return bytesToHex(md.digest());
    }


    private static String bytesToHex(byte[] digest) {
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
