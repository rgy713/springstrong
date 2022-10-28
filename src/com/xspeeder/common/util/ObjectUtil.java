

package com.xspeeder.common.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ObjectUtil {

	
	public static Object createDynamicObject(String value, 
											Class<?> cl) {

		Object obj = null;
		try {
			
			Class<?>[] paramTypes = new Class<?>[1];
			paramTypes[0] = String.class;

		
			Object[] paramValues = new Object[1];
			paramValues[0] = value;

		
			String className = cl.getName();

			
			Class<?> cls = Class.forName(className);

			
			Constructor<?> co = cls.getConstructor(paramTypes);

			
			obj = co.newInstance(paramValues);
		} catch (Exception ex) {
			// ignore
		}

		return obj;
	}


	public static Map<String, Object> ConvertBeanToMap(Object bean) {

		Field[] fields = bean.getClass().getDeclaredFields();
		
	
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		String		fld_name		= null;
		String		getter			= null;
		Class<?>[]	parameterTypes	= null;

		try {
			for (Field f : fields) {
				
				fld_name = f.getName();
				
				
				getter = "get" + fld_name.substring(0, 1).toUpperCase() + fld_name.substring(1);
				
				
				Method m = bean.getClass().getMethod(getter, parameterTypes);
				if (m != null) {
					Object value = m.invoke(bean);
					
					
					map.put(fld_name, value);
				}
			}
		} catch (Exception ex) {
			// ignore
		}

		return map;
	}
}
