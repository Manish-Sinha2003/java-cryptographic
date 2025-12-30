package com.iispl.jca;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

public class MessageDigestExample {
	
	public static void main(String[] arg) throws NoSuchAlgorithmException {
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter the input:");
		
		String message=sc.nextLine();
		
		MessageDigest md=MessageDigest.getInstance("MD5");
		
		md.update(message.getBytes());
		
		byte[] digest=md.digest();
	
		
		byte[] combine=new byte[digest.length+message.getBytes().length];
		
		System.arraycopy(digest, 0, combine, 0, digest.length);
		System.arraycopy(message.getBytes(), 0, combine, digest.length,message.getBytes().length);
		
		String encodedText=Base64.getEncoder().encodeToString(combine);
		
		System.out.println("Encoded: "+encodedText);
		
		byte[] decodedText=Base64.getDecoder().decode(encodedText);
		
		String decodedMsg=new String(decodedText);
		
		System.out.println("Decoded Msg: "+decodedMsg);
		
		StringBuffer digestMsg=new StringBuffer();
		for(int i=0;i<digest.length;i++) {
			digestMsg.append(Integer.toHexString(0xFF & digest[i]));
		}
		
		for(int i=digest.length;i<decodedMsg.length();i++) {
			System.out.print(decodedMsg.charAt(i));
		}
		
		System.out.println();
		
		System.out.println("DecodedDigestMsg: "+digestMsg.toString());

		StringBuffer sb=new StringBuffer();
		
		for(int i=0;i<digest.length;i++) {
			sb.append(Integer.toHexString(0xFF & digest[i]));
		}
		
		System.out.println("Hex formate: "+sb.toString());
	}
}
