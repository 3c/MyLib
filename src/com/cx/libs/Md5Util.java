package com.cx.libs;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;


/**
 * @author CX
 *
 */
public class Md5Util {
	public static String toMd5(String strText){
		if(strText == null || strText.length() == 0){
			return null;
		}
		try {
			return toMd5(strText.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	public static String toMd5(byte[] bytes) {
		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(bytes);
			return toHexString(algorithm.digest(), "");
		} catch (Exception e) {
			return null;
		}
	}

	private static String toHexString(byte[] bytes, String separator) {
		StringBuilder hexString = new StringBuilder();
		for (byte b : bytes) {
			hexString.append(Integer.toHexString(0xFF & b)).append(separator);
		}
		return hexString.toString();
	}

}
