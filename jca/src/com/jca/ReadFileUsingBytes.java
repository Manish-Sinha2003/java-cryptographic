package com.jca;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadFileUsingBytes {

    public static void main(String[] args) {

        byte[] buffer = new byte[ 8192];

        try {
            FileInputStream fis = new FileInputStream("hello.txt");

            BufferedInputStream bis = new BufferedInputStream(fis);
            
            int bytesRead;

            while ((bytesRead = bis.read(buffer)) != -1) {

                System.out.println(new String(buffer, 0, bytesRead));
            }

            bis.close();
            fis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
