package com.mobitide.common.utils;

import java.io.UnsupportedEncodingException;

/**
 * @author CX
 * 
 */
public final class MUrlDeEncodeUtil {
	/**
	 * @param uri
	 * @return encode uri
	 */
	public static String urlEncode(String uri) {
		if (MStringUtil.isOK(uri)) {
			return java.net.URLEncoder.encode(uri);
		}
		return "";
	}

	/**
	 * @param uri
	 * @return encode uri
	 */
	public static String urlEncode(String uri, String charc) {
		try {
			return java.net.URLEncoder.encode(uri, charc);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}

	}

	/**
	 * @param uri
	 * @return decode uri
	 */
	public static String urlDecode(String uri) {
		return java.net.URLDecoder.decode(uri);
	}

}
