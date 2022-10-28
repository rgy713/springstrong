/**
 * 
 */
package com.xspeeder.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidUtil {
	
	public static boolean validEmail(String e_mail) {
		
		// E-mail
		/*
		 * 
		 * ^						#start of the line
		 *   [_A-Za-z0-9-\\+]+		#  must start with string in the bracket [ ], must contains one or more (+)
		 *   (						#   start of group #1
		 *     \\.[_A-Za-z0-9-]+	#     follow by a dot "." and string in the bracket [ ], must contains one or more (+)
		 *   )*						#   end of group #1, this group is optional (*)
		 *     @					#     must contains a "@" symbol
		 *      [A-Za-z0-9-]+      	#       follow by string in the bracket [ ], must contains one or more (+)
		 *       (					#         start of group #2 - first level TLD checking
		 *        \\.[A-Za-z0-9]+  	#           follow by a dot "." and string in the bracket [ ], must contains one or more (+)
		 *       )*					#         end of group #2, this group is optional (*)
		 *       (					#         start of group #3 - second level TLD checking
		 *        \\.[A-Za-z]{2,}  	#           follow by a dot "." and string in the bracket [ ], with minimum length of 2
		 *       )					#         end of group #3
		 * $						#end of the line
		 */
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
		Pattern pattern	= Pattern.compile(EMAIL_PATTERN);
		Matcher matcher	= pattern.matcher(e_mail);
		return matcher.matches();
	}
	
	public static boolean validMobile(String mobile) {
		

		String MOBILE_PATTERN = "^(\\(\\d{3}\\))?[1][0-9]{10}$";
		
		Pattern pattern	= Pattern.compile(MOBILE_PATTERN);
		Matcher matcher	= pattern.matcher(mobile);
		
		return matcher.matches();
	}
	
	public static boolean validIdentificationNum(String identificationNum) {
		// 身份证查询 URL - http://mytools.duapp.com/tooss/sfz/
		// 500233198410232086
		// http://www.youdao.com/smartresult-xml/search.s?type=id&q=500233198410232086
		// http://www.youdao.com/smartresult-xml/search.s?jsFlag=true&type=id&q=500233198410232086
		
		// 
		boolean ret = true;
		try {
			String			url		= "http://www.youdao.com/smartresult-xml/search.s?jsFlag=true&type=id&q=" + identificationNum;
			URL				content	= new URL(url);
	        BufferedReader	in		= new BufferedReader(new InputStreamReader(content.openStream()));

	        StringBuffer	result	= new StringBuffer();
	        String			line;
	        while ((line = in.readLine()) != null) {
	        	result.append(line);
	        }
	        in.close();
	        
	        int pos = result.indexOf("identitycard");	
	        if (pos < 0) {								
	        	ret = false;
	        }
		} catch (Exception ex) {
			ret = false;
		}
		
		
		ret = true;
		return ret;
	}
}
