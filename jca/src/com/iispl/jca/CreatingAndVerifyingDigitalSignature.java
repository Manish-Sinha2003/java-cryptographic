package com.iispl.jca;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Scanner;

public class CreatingAndVerifyingDigitalSignature {
	
	public static void main(String[] args) throws SignatureException, UnsupportedEncodingException, InvalidKeyException {
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter the text: ");
		
		String msg=sc.nextLine();
		
		try {
			KeyPairGenerator keyPaiGen = KeyPairGenerator.getInstance("DSA");
			
			keyPaiGen.initialize(2048);
			
			KeyPair pair=keyPaiGen.generateKeyPair();
			
			PrivateKey pvtKey=pair.getPrivate();
			
			Signature signature=Signature.getInstance("SHA256withDSA");
			
			signature.initSign(pvtKey);
			
			byte[] bytes=msg.getBytes();
			
			signature.update(bytes);
			
			byte[] sign=signature.sign();
			
			System.out.println("Digital signature for given text: "+new String(sign,"UTF8"));
			
			signature.initVerify(pair.getPublic());
			signature.update(bytes);
			
			boolean bool= signature.verify(sign);
			
			if(bool) System.out.println("signature verified");
			else System.out.println("signature Not verified");
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
