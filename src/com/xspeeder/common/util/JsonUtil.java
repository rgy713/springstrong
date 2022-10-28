
package com.xspeeder.common.util;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.xspeeder.common.exception.XException;
import com.xspeeder.common.util.json.DoubleTypeAdapter;
import com.xspeeder.common.util.json.ISODateTypeAdapter;
import com.xspeeder.ws.common.WSErrors;

public class JsonUtil {


	public static <T> T fromJson(String json, Class<T> classOfT) 
	throws XException {
		try {
			return instance().fromJson(json, classOfT);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new XException("ERR_JSON_ERROR" + " " + ex.getMessage());
		}
	}

	
	public static <T> T fromJson(String json, Type listType) 
	throws XException {
		try {
			return instance().fromJson(json, listType);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new XException("ERR_JSON_ERROR" + " " + ex.getMessage());
		}
	}


	public static String toJson(Object src) 
	throws XException {
		try {
			return instance().toJson(src);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new XException("ERR_JSON_ERROR" + " " + ex.getMessage());
		}
	}
	
	
	private static Gson instance() {
		GsonBuilder builder = new GsonBuilder();

		builder.registerTypeAdapter(java.util.Date.class, new ISODateTypeAdapter());
		builder.registerTypeAdapter(Double.class, new DoubleTypeAdapter());
		Gson gson = builder.create();
		
		return gson;
	}
	
	
	public static Gson slashedGson()
	{
		Gson gson = new GsonBuilder().disableHtmlEscaping().registerTypeAdapter(String.class, new JsonSerializer<String>(){
			@Override
			public JsonElement serialize(String src, Type typeOfSrc, JsonSerializationContext context) {
				
				if(	src.length() > 4 ){
    				String temp = src.substring(src.length()-2, src.length());
    				if( src.substring(0, 2).equals("[{") && temp.equals("}]") ){
    					Gson gson = new GsonBuilder().create();
    					
    					Type collectionType = new TypeToken<List<Map<String, Object>>>(){}.getType();
						List<Map<String, Object>> object = gson.fromJson(src, collectionType);
						for( int i = 0; i < object.size(); i++){
							Double idValue = (Double)object.get(i).get("id");
							if( idValue != null)
								object.get(i).replace("id", (Integer)idValue.intValue());
						}
						
						JsonElement result = gson.toJsonTree(object, collectionType);
						return result;
    				}
				}
				return new JsonPrimitive(src);
			 }
		}).setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create();
		
		return gson;
	}
}
