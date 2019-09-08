package com.hbe.ms.common.utils;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class contains utility methods for Encrypting and Decrypting passwords
 * 
 * @author mrahaman
 * 
 */

public class HBEEncrytDecrypt {

	private static final Logger LOGGER = LoggerFactory.getLogger(HBEEncrytDecrypt.class);

	private static final String ALGO = "AES";
	private static final String ENCRYPTION_KEY = "ENCRYPTION_KEY";

	private HBEEncrytDecrypt() {
	}

	/**
	 * This method is used to encrypt the data for a given string *
	 * 
	 * @param Data
	 * @return encrypted string
	 */
	public static String encrypt(String data) {
		try {
			Key key = generateKey();
			Cipher c = Cipher.getInstance(ALGO);
			c.init(Cipher.ENCRYPT_MODE, key);
			byte[] encVal = c.doFinal(data.getBytes());
			byte[] encoded = Base64.encodeBase64(encVal);
			return new String(encoded);
		} catch (Exception e) {
			LOGGER.error(data, e);
		}
		return "";
	}

	/**
	 * This method is used to dencrypt the data of a given string
	 * 
	 * @param encryptedData
	 * @return dencrypted string
	 */
	public static String decrypt(String encryptedData) {
		try {
			Key key = generateKey();
			Cipher c = Cipher.getInstance(ALGO);
			c.init(Cipher.DECRYPT_MODE, key);
			byte[] decordedValue = Base64.decodeBase64(encryptedData.getBytes());
			byte[] decValue = c.doFinal(decordedValue);
			return new String(decValue);
		} catch (Exception e) {
			LOGGER.error(encryptedData, e);
		}
		return encryptedData;
	}

	private static Key generateKey() {
		String systemKeyValue = System.getProperty(ENCRYPTION_KEY);
		return new SecretKeySpec(systemKeyValue.getBytes(), ALGO);
	}

}