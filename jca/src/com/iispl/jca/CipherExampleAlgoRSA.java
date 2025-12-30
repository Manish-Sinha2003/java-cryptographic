package com.iispl.jca;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;


public class CipherExampleAlgoRSA {
	
		public static void main(String[] args) throws Exception {
			try {
				KeyPair key = keyGenerator();
				
				Key privateKey=key.getPrivate();
				
				Key PublicKey=key.getPublic();
				
				Scanner sc = new Scanner(System.in);

				System.out.println("Enter the input:");

				String originalData = sc.nextLine();

				System.out.println("Original Data: " + originalData);

				String encryptedData = encrypt(originalData, PublicKey);

				System.out.println("Encrypted Encoded Data : " + encryptedData);

				String decryptedData = decrypt(encryptedData, privateKey);

				System.out.println("Decrypted or Original Data: " + decryptedData);

			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private static String decrypt(String data, Key key) throws Exception {
			// TODO Auto-generated method stub
			Cipher cipher = Cipher.getInstance("RSA");

			cipher.init(Cipher.DECRYPT_MODE, key);

			byte[] decryptByte = cipher.doFinal(Base64.getDecoder().decode(data));

			return new String(decryptByte);
		}

		private static String encrypt(String data, Key key) throws Exception {
			// TODO Auto-generated method stub

			Cipher cipher = Cipher.getInstance("RSA");

			cipher.init(Cipher.ENCRYPT_MODE, key);

			byte[] encryptByte = cipher.doFinal(data.getBytes());

			return Base64.getEncoder().encodeToString(encryptByte);
		}

		private static KeyPair keyGenerator() throws NoSuchAlgorithmException {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
			
			SecureRandom secRandom = new SecureRandom();

			keyGen.initialize(2048);

			return keyGen.generateKeyPair();
		}
	}


