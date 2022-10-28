
package com.xspeeder.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;

public class SecurityUtil {
	
	/**
	 * MD5
	 */
	public static final String MD5		= "MD5";
	
	/**
	 * SHA-256
	 */
	public static final String SHA_256	= "SHA-256";
	
	public static String encryptPwd(String pwd) {

		String enc_pwd = "";
		if (!StringUtil.isEmpty(pwd)) {
			enc_pwd = pwd;
		}
		return enc_pwd;
	}
	
	public static String encryptPwdWithSHA256(String pwd) {

		String enc_pwd = "";
		if (!StringUtil.isEmpty(pwd)) {
			enc_pwd = msgDigest(pwd.getBytes(), SHA_256);
		}
		return enc_pwd;
	}

	public static boolean verifyPwd(String candidate, 
									String hashed) {
		return hashed.equals(candidate);
	}

	public static boolean verifyPwdWithSHA256(String candidate, 
											  String hashed) {
		String enc_pwd = msgDigest(candidate.getBytes(), SHA_256);
		return hashed.equals(enc_pwd);
	}
	
	public static String generateAliasVerifyCode(String	alias, 
												Date	date) {
		String	str	= alias + date;
		String	ret	= msgDigest(str.getBytes(), MD5);
		
		return ret;
	}
	
	public static String generateMailVerifyCode(String	e_mail, 
												Date	date) {
		String	str	= e_mail + date;
		String	ret	= msgDigest(str.getBytes(), MD5);
		
		return ret;
	}
	
	public static String generateMobileVerifyCode(String mobile, 
													Date date) {
		String	str	= mobile + date;
		String	ret	= msgDigest(str.getBytes(), MD5);
		
		ret = ret.substring(0, 9).toUpperCase();
		
		return ret;
	}
	
	public static String generateFileName(String str) {
		
		String ret;
		
		if (StringUtil.isEmpty(str)) {
			ret = "";
		} else {
			
			Random rnd = new Random();
			int seed = rnd.nextInt(100);
			ret = seed + "_" + str;
			
			ret = msgDigest(str.getBytes(), MD5);
		}
		
		return ret;
	}
	
	public static String msgDigest(	byte[] in, 
									String algorithm) {
		String result = null;
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			md.update(in);
			
			byte			hash[]	= md.digest();
			StringBuffer	sb		= new StringBuffer();
			for (int i = 0 ; i < hash.length ; i++) {
				sb.append(String.format("%02x", hash[i]));
			}
			
			result = sb.toString();
			
		} catch(NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	public static String makeAppKey( String pwd, String mobile)
	{
		return pwd + SecurityUtil.encryptPwdWithSHA256(mobile);
	}
}
