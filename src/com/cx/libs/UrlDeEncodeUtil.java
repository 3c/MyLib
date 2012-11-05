package com.cx.libs;

/**
 * @author CX
 * 
 */
public final class UrlDeEncodeUtil {
	/**
	 * @param uri
	 * @return encode uri
	 */
	public static String urlEncode(String uri) {
		return java.net.URLEncoder.encode(uri);
	}

	/**
	 * @param uri
	 * @return decode uri
	 */
	public static String urlDecode(String uri) {
		return java.net.URLDecoder.decode(uri);
	}

}
