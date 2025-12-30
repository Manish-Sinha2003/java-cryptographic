package com.iispl.jca;

import java.util.Base64;

public class BasicEncodingDecodingExample {
	
	public static void main(String[] args) {
		String msg="Hello, How are You!!!!";

		String encodedText=Base64.getEncoder().encodeToString(msg.getBytes());
		
		System.out.println("original msg: "+ msg);
		
		System.out.println("Encoded msg: "+ encodedText);
		
		byte[] decodedText=Base64.getDecoder().decode(encodedText);
		
		System.out.println("Encoded msg: "+ new String(decodedText));
	}
	
}
