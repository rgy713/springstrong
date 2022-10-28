/**
 * Copyright University Development Team
 * All rights reserved.
 * 
 */

package com.xspeeder.common.util;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

import com.chinese.identifier.ChineseCharIdentifier;

public class FilterUtil {
	private static final int 	CONST_DEFAULT_START 	= 0;
	private static final int 	CONST_DEFAULT_LIMIT		= 100;
	
	public static String filterStrIDS(String ids){
		if(ids == null || "".equals(ids)){
			return null;
		}
		
		ids = ids.replaceAll("\\s", "");
		ids = ids.replaceAll("\\,+", ",");
		
		if(ids == null || "".equals(ids)){
			return null;
		}
		
		if(ids.lastIndexOf(",") == (ids.length() - 1)){
    		ids = ids.substring(0, ids.length() - 1);
    	}    	
		return ids;
	}
	
	public static Integer filterStart(Integer 	start){
		Integer 	ret = CONST_DEFAULT_START;
		
		if(start == null){
			ret = CONST_DEFAULT_START;
		}
		else {
			if(start < CONST_DEFAULT_START){
				ret = CONST_DEFAULT_START;
			} else {
				ret = start;
			}
		}
		
		return ret;
	}
	
	public static Integer filterLimit(Integer 	limit){
		Integer 	ret = CONST_DEFAULT_LIMIT;
		
		if(limit == null){
			ret = CONST_DEFAULT_LIMIT;
		}
		else {
			if(limit > CONST_DEFAULT_LIMIT){
				ret = CONST_DEFAULT_LIMIT;
			} else {
				if(limit <= CONST_DEFAULT_START){
					ret = CONST_DEFAULT_LIMIT;
				} else {
					ret = limit;
				}
			}
		}
		
		return ret;
	}
	
	public static String 	extractAliasesFromText(String	str_text){
		String 	ret = "";
		
		if(str_text == null || "".equals(str_text)){
			return null;
		}
		
		str_text = str_text.replaceAll("\\s+", " ");
		str_text = str_text.replaceAll("\\,+", ",");
		
		if(str_text == null || "".equals(str_text)){
			return null;
		}
		
		String[] arr_str_text = str_text.split("\\s");
		List<String>	arr_ret = new ArrayList<String>();
		for(String one_text : arr_str_text){
			if(one_text.lastIndexOf("@") != -1){
				Integer sIndex = one_text.lastIndexOf("@");
				String alias = one_text.substring(sIndex+1, one_text.length());
				alias = alias.replaceAll("\\,+", "");
				
				if(arr_ret != null && arr_ret.size() > 0 ){
					boolean isExist = false;
					for(int i = 0; i < arr_ret.size(); i ++){
						if(arr_ret.get(i).equals(alias)){
							isExist = true;
						}
					}
					if(!isExist){
						arr_ret.add(alias);
					}
				} else {
					arr_ret.add(alias);
				}
			}
		}
		
		if(arr_ret != null && arr_ret.size() > 0){
			for(int j = 0; j < arr_ret.size(); j ++){
				ret += arr_ret.get(j);
				if(j != arr_ret.size() - 1){
					ret += ",";
				}
			}
		}
		return	ret; 
	}
	
	public static String 	convertAliasToAlphabet(String 	alias) throws Exception{
		String 	ret = "";
		
		if(alias == null || "".equals(alias)){
			return null;
		}
		
		alias = alias.replaceAll("\\s+", "");
		if(alias == null || "".equals(alias)){
			return null;
		}
		alias = alias.toLowerCase();
		
		char 	alias_sl = alias.charAt(0);
		
		boolean bChinese = ChineseCharIdentifier.isChineseChar(Integer.valueOf(alias_sl));
		
		if(bChinese){
			HanyuPinyinOutputFormat outFmt = new HanyuPinyinOutputFormat();
			outFmt.setCaseType(HanyuPinyinCaseType.LOWERCASE);
			outFmt.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
			outFmt.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);
			String[] 	pinyins = PinyinHelper.toHanyuPinyinStringArray(alias_sl, outFmt);
			
			for(String pinyin: pinyins){
				if(ret != null && !"".equals(ret) && ret.indexOf("," + String.valueOf(pinyin.charAt(0)) + ",") != -1){
					
				} else {
					ret += String.valueOf(pinyin.charAt(0)) + ",";
				}		
			}
			if(ret != null && !"".equals(ret)){
				ret = ret.substring(0, ret.length() - 1);
			}
		} else if(alias_sl >= 'a' && alias_sl <= 'z'){
			ret = String.valueOf(alias_sl);
		} else {
			ret = "#";
		}		
		
		return	ret.substring(0,1); 
	}
}
