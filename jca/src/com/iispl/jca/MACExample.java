package com.iispl.jca;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class MACExample {
	static String toHashString(byte[] hmac) {
		StringBuffer hexString = new StringBuffer();
		for(int i=0;i<hmac.length;i++) {
			
			hexString.append(Integer.toHexString(0xFF & hmac[i]));
		}
		return hexString.toString();
	}
	
	public static String getMac(String message,String passcode) throws NoSuchAlgorithmException, InvalidKeyException {
		SecretKeySpec secretkeySpec = new SecretKeySpec(passcode.getBytes(),"HmacSHA256");
		
		Mac hmacSha256 = Mac.getInstance("HmacSHA256");
		
		hmacSha256.init(secretkeySpec);
		
		byte[] hmac = hmacSha256.doFinal(message.getBytes());
		String str = toHashString(hmac);
		System.out.println(str);
		return str;
		}
	
	public static void verifyMessage(String msg,String macHash,String secretKey) throws NoSuchAlgorithmException, InvalidKeyException {
		SecretKeySpec secretKeySpec=new SecretKeySpec(secretKey.getBytes(),"HmacSHA256");
		
		Mac hmacSha256 = Mac.getInstance("HmacSHA256");
		
		hmacSha256.init(secretKeySpec);
		
		byte[] calculatedHmac= hmacSha256.doFinal(msg.getBytes());
		
		String str=toHashString(calculatedHmac);
		
		if(str.equals(macHash)) {
			System.out.println("valid msg");
		}else {
			System.out.println("Invalid MAC: Message integrity compromised.");
		}
	}
	
	public static void main(String[] arg) {
		String originalMessage ="Hello, This is a secret message";
		
		String passcode="YourSecuretKey";
		
		try {
			String mac=getMac(originalMessage,passcode);
			verifyMessage(originalMessage,mac,passcode);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
