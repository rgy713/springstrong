
package com.xspeeder.common.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ListUtil {

	
	public static Object[] extract( List<Map<String, Object>> heap, String key )
	{
		if( ListUtil.isEmpty(heap) )
			return null;
		
		Object[] data = new Object[heap.size()];
		
		for( int i = 0; i < heap.size(); i++ ) 
		{
			Map<String, Object> one = heap.get(i);
			data[i] = one.get(key);
		}
		
		return data;
	}


	
	public static String extractCommaString( List<Map<String, Object>> heap, String key )
	{
		if( ListUtil.isEmpty(heap) )
			return null;
		
		String result = "";
		
		for( int i = 0; i < heap.size(); i++ ) 
		{
			Map<String, Object> one = heap.get(i);
			result = result + one.get(key) + ",";
		}
		
		return result.substring(0, result.length()-1);
	}
	
	
	
	public static boolean isEmpty( @SuppressWarnings("rawtypes") List heap )
	{
		if( heap == null )
			return true;
		
		if( heap.size() == 0 )
			return true;
		
		return false;
	}
	
	
	public static boolean isEmpty( Object[] array )
	{
		if( array == null )
			return true;
		
		if( array.length != 0 )
			return false;
		
		return true;
	}
	
	
	public static boolean isEmpty( @SuppressWarnings("rawtypes") Map heap )
	{
		if( heap == null )
			return true;
		
		if( heap.isEmpty() )
			return true;
		
		return false;
	}
	
	
	public static Map<Object, Object> list2json_map( List<Map<String, Object>> heap, String key_field ) 
	{
		if( ListUtil.isEmpty(heap) )
			return null;
			
		Iterator<Map<String, Object>> iter = heap.iterator();
		
		Map<Object, Object> result = new HashMap<Object, Object>();
		
		while(iter.hasNext())
		{
			Map<String, Object> one = iter.next();
			
			result.put(one.get(key_field), one);
		}
		
		return result;
	}
}
