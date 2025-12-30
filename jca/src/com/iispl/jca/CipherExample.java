package com.iispl.jca;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

public class CipherExample {

	public static void main(String[] args) throws Exception {
		try {
			Key key = keyGenerator();
			
			Scanner sc = new Scanner(System.in);

			System.out.println("Enter the input:");

			String originalData = sc.nextLine();

			System.out.println("Original Data: " + originalData);

			String encryptedData = encrypt(originalData, key);

			System.out.println("Encrypted Encoded Data : " + encryptedData);

			String decryptedData = decrypt(encryptedData, key);

			System.out.println("Decrypted or Original Data: " + decryptedData);

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String decrypt(String data, Key key) throws Exception {
		// TODO Auto-generated method stub
		Cipher cipher = Cipher.getInstance("AES");

		cipher.init(Cipher.DECRYPT_MODE, key);

		byte[] decryptByte = cipher.doFinal(Base64.getDecoder().decode(data));

		return new String(decryptByte);
	}

	private static String encrypt(String data, Key key) throws Exception {
		// TODO Auto-generated method stub

		Cipher cipher = Cipher.getInstance("AES");

		cipher.init(Cipher.ENCRYPT_MODE, key);

		byte[] encryptByte = cipher.doFinal(data.getBytes());

		return Base64.getEncoder().encodeToString(encryptByte);
	}

	private static Key keyGenerator() throws NoSuchAlgorithmException {
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		
		SecureRandom secRandom = new SecureRandom();

		keyGen.init(secRandom);

		Key key = keyGen.generateKey();

		return key;
	}
}
