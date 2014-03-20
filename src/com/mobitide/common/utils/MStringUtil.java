package com.mobitide.common.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MStringUtil {
    public static String inputStream2String(InputStream is) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }

    public static InputStream String2inputStream(String str) {

        InputStream inputStream = new ByteArrayInputStream(str.getBytes());
        return inputStream;

    }

    public static String getLastBySplit(String sourceString, String split) {
        return sourceString.substring(sourceString.lastIndexOf(split) + 1, sourceString.length());
    }

    /**
     * 验证Str是否可用
     * 
     * @param source
     * @return true if str is not null and not ""
     */
    public static boolean isOK(String source) {
        return source != null && !"".equals(source.trim());
    }

}
