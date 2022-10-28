

package com.xspeeder.common.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

public class NumberUtil {

	private static final int MONEY_DEC_PLACE = 2;
	
	
    public static float roundMoney(float money) {
    	return round(money, MONEY_DEC_PLACE);
    }

	
    private static float round(float val, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(val));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
    
    
    public static Long toLong(Object v) 
    {
    	if( v instanceof Long )
    		return (Long)v;
    	
    	else if( v instanceof Integer )
    		return ((Integer)v).longValue();
    	
    	else if( v instanceof Byte )
    		return ((Byte)v).longValue();
    	
    	else if( v instanceof BigInteger )
    		return ((BigInteger)v).longValue();
    	
    	else if( v instanceof Double )
    		return ((Double)v).longValue();

    	else if( v instanceof Float )
    		return ((Float)v).longValue();
    	
    	else if( v instanceof String )
    		return Long.parseLong(v.toString(), 10);
    	
    	else if( v instanceof BigDecimal )
    		return ((BigDecimal)v).longValue();
    	
    	return null;
    }
   
    public static Integer toInt(Object v)
    {	
    	if( v instanceof Integer )
    		return (Integer)v;
    	
    	else if( v instanceof Long )
    		return ((Long)v).intValue();
    	
    	else if( v instanceof Byte )
    		return ((Byte)v).intValue();
    	
    	else if( v instanceof BigInteger )
    		return ((BigInteger)v).intValue();
    	
    	else if( v instanceof Double )
    		return ((Double)v).intValue();

    	else if( v instanceof Float )
    		return ((Float)v).intValue();
    	
    	else if( v instanceof String )
    		return Integer.valueOf((String)v);
    	
    	else if( v instanceof BigDecimal )
    		return ((BigDecimal)v).intValue();
    	
    	return null;
    }
    
    
    public static Double toDouble(Object v)
    {	
    	if( v instanceof Double )
    		return (Double)v;

    	else if( v instanceof Float )
    		return ((Float)v).doubleValue();
    	
    	else if( v instanceof Integer )
    		return ((Integer)v).doubleValue();
    	
    	else if( v instanceof Long )
    		return ((Long)v).doubleValue();
    	
    	else if( v instanceof Byte )
    		return ((Byte)v).doubleValue();
    	
    	else if( v instanceof BigInteger )
    		return ((BigInteger)v).doubleValue();
    	
    	else if( v instanceof String )
    		return Double.valueOf((String)v);
    	
    	return null;
    }
    
    
    public static Float toFloat(Object v)
    {	
    	if( v instanceof Float )
    		return ((Float)v);

    	else if( v instanceof Double )
    		return ((Double)v).floatValue(); 
    	
    	else if( v instanceof Integer )
    		return ((Integer)v).floatValue();
    	
    	else if( v instanceof Long )
    		return ((Long)v).floatValue();
    	
    	else if( v instanceof Byte )
    		return ((Byte)v).floatValue();
    	
    	else if( v instanceof BigInteger )
    		return ((BigInteger)v).floatValue();
    	
    	else if( v instanceof String )
    		return Float.valueOf((String)v);
    	
    	return null;
    }
    
    public static Integer floating2int( String s )
    {
    	if( StringUtil.isEmpty(s) )
    		return null;
    	
    	Integer p = s.indexOf("E");
		if( p >= 0 )
		{
			String f = s.substring(0, p);
			String e = s.substring(p+1);
			
			Double df = Double.valueOf(f);
			Double de = Double.valueOf(e);
			
			Double real = df * Math.pow(10.0, de);
			
			return real.intValue();
		}
		else
		{
			return Integer.valueOf(s);
		}
    }
	public static String generateVerifyCode() {

		Random rnd = new Random();
		Integer n = rnd.nextInt(99999);
		
		int k = 10;
		
		while( n <= 10000 ) {

			if( k == 0 ) {
				int sz = n.toString().length();
				
				int left = 5 - sz;
				
				String ret =n.toString();
				
				for( int p = 0; p < left; p++ )
					ret = ret + "0";
				
				return ret;
			}
			
			n = rnd.nextInt(99999);
			
			k--;
		}
		
		return n.toString();
	}
}
