

package com.xspeeder.common.util;

import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class StringUtil {

	public static boolean isEmpty(String str) {
		if (null == str) {
			return true;
		}

		if ("".equals(str)) {
			return true;
		}

		return false;
	}

	public static String encodeURL(String str) {
		if (null == str) {
			return "";
		}

		String ret = "";
		try {
			ret = URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return ret;
	}
	
	public static String inputStream2String( InputStream is ) throws Exception
	{
		StringWriter writer = new StringWriter();
    	IOUtils.copy(is, writer, "UTF-8");
    	String str = writer.toString();
    	
    	return str;
	}
	
	public static String dotdotdotString( String str, Integer size )
	{
		if( str.length() > size )
			return str.substring(0, size) + "...";
		
		return str;
	}

	public static String replaceWithStar( String data, Integer lpos, Integer rpos )
	{
		if( data == null || data.length() <= rpos + lpos )
			return "-";
		
		String f = data.substring(0, lpos);
		String l = StringUtils.right(data, rpos);
		
		String result = f;
		for( int i = 0; i < data.length() - rpos - lpos; i++ )
		{
			result = result + "*";
		}
		
		result = result + l;
		
		return result;
	}
}
