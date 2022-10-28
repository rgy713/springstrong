
package com.websocket.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.websocket.WebsocketEndPoint;
import com.xspeeder.common.util.ContextUtils;
import com.xspeeder.common.util.JsonUtil;

public class WebsocketUtil1 {
	
	public static final Integer CONST_REALTIME_CHATTING				= 1;
	public static final Integer CONST_REALTIME_CHATTING_READING		= 2;
	public static final Integer CONST_BUY_PSM_PRODUCT				= 3;
	
	
	public static WebsocketEndPoint getWebsocketEndPoint(){
		ServletContext sc = ContextUtils.servletContext();
		WebApplicationContext wactx = ContextUtils.getWebAppCtx(sc);
		
		return (WebsocketEndPoint)wactx.getBean("websocketHandler");
	}


	public static String makeJsonResult(
				Integer kind,
				Object data
			)
	{
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("kind", kind);
		result.put("data", data);

		Gson gson = JsonUtil.slashedGson();
        String jsonResult = gson.toJson( result );
        
        return jsonResult;
        
	}
	

    public static Integer getIsConnected(Long user_id) {
    	
    	WebsocketEndPoint wsEndPoint = WebsocketUtil1.getWebsocketEndPoint();
    	
    	return wsEndPoint.getIsConnected(user_id);
    	
    }
    
    public static void sendChatting(
    		Long chatting_id,
    		Integer chatting_kind,
    		String content,
    		String file_name,
    		Long sender,
    		Long receiver,
    		Date send_date,
    		Integer state,
    		WebsocketEndPoint wsEndPoint
    		)
    {
    	
    	if( wsEndPoint == null )
    		wsEndPoint = WebsocketUtil.getWebsocketEndPoint();
		
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("chatting_id", chatting_id);
		data.put("chatting_kind", chatting_kind);
		data.put("content", content);
		if( file_name != null) data.put("file_name", file_name);
		data.put("sender", sender);
		data.put("receiver", receiver);
		data.put("send_date", send_date);
		data.put("state", state);
		
		
		String jsonResult = WebsocketUtil1.makeJsonResult(CONST_REALTIME_CHATTING, data);
		
		
		wsEndPoint.sendMessage(receiver, jsonResult);
    }
    
    
    public static void sendChattingReading(
    		Long sender,
    		Long receiver,
    		Integer count
    		)
    {
    	
    	
		WebsocketEndPoint wsEndPoint = WebsocketUtil1.getWebsocketEndPoint();
		
	
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("sender", sender);
		data.put("count", count);
		
		
		String jsonResult = WebsocketUtil1.makeJsonResult(CONST_REALTIME_CHATTING_READING, data);
		
		
		wsEndPoint.sendMessage(receiver, jsonResult);
    }

   
    public static void sendBuyProduct(
    		String content
    		)
    {
    	
    	
		WebsocketEndPoint wsEndPoint = WebsocketUtil1.getWebsocketEndPoint();
		
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("content", content);
		
		
		String jsonResult = WebsocketUtil1.makeJsonResult(CONST_BUY_PSM_PRODUCT, data);
		
		
		wsEndPoint.sendMessage(jsonResult);
    }
    
}
