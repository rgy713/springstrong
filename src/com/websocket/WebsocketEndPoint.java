package com.websocket;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.websocket.util.WebsocketUtil;
import com.xspeeder.common.util.DateTimeUtil;
import com.xspeeder.common.util.NumberUtil;
  
public class WebsocketEndPoint extends TextWebSocketHandler {
     
	static Logger log = Logger.getLogger(WebsocketEndPoint.class.getName());
	
	
    private Map<Long, Map<String, WebSocketSession>> clients;
    
    
    private Map<Long, Map<String, Map<String, Object>>> activities;
    
    
    private Map<String, Long> session_uid_map;
  
    
    public WebsocketEndPoint() {
        super();
        clients = new HashMap<Long, Map<String, WebSocketSession>>();    
        session_uid_map = new HashMap<String, Long>();
        
        activities = new HashMap<Long, Map<String, Map<String, Object>>>();
    }
  
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session)
            throws Exception {
        super.afterConnectionEstablished(session);
        
        log.info("Connected!session_id:" + session.getId());         
        
    }
  
    
    @Override
    protected void handleTextMessage(WebSocketSession session,
            TextMessage message) throws Exception {
    	
    	String payloadMessage = message.getPayload();
    	
		Gson gson = new Gson();    	
    	Type collectionType = new TypeToken<Map<String, Object>>(){}.getType();
    	Map<String, Object> data = gson.fromJson(payloadMessage, collectionType);
    	
    	Integer msg_kind = NumberUtil.toInt(data.get("kind"));
    	switch(msg_kind){
    	case 1000: 
    	{
    		Long user_id = NumberUtil.toLong(data.get("uid"));   
    		log.info("[login] user_id:" + user_id.toString());
            
            if( user_id != null ){
            	
            	if( clients.get(user_id) == null )
            		clients.put(user_id, new HashMap<String, WebSocketSession>());
            	else {
            		Map<String, Object> params = new HashMap<String, Object>();
					params.put("chatting_id", null);
					/*params.put("chatting_kind", Const.IDX_SYSMSG_FORCE_LOGOUT);*/
					params.put("content", "Logout");
					/*params.put("sender", Const.CONST_CHATTING_SYSID);*/
					params.put("receiver", user_id);
					params.put("send_date", DateTimeUtil.utcNow());
					params.put("state", null);
					
					
					String jsonResult = WebsocketUtil.makeJsonResult(WebsocketUtil.CONST_REALTIME_CHATTING, params);
					
					
					this.sendMessage(user_id, jsonResult);
					
					log.info("Send logout message to already logged users");
            	}
            	
            	clients.get(user_id).put(session.getId(), session);
            	session_uid_map.put(session.getId(), user_id);
            	
            }
    		break;
    	}
    	case 1001:
    	{	
    		Long user_id = NumberUtil.toLong(data.get("uid"));   
    		log.info("[logout] user_id:" + user_id.toString());
    		
        	session_uid_map.remove(session.getId());
            
            clients.get(user_id).remove(session.getId());
            
            if( clients.get(user_id).size() == 0 )
            	clients.remove(user_id);
    		break;
    	}
    	case 1002: // ping
    	{    	
    		data.put("kind", 1003);
    		session.sendMessage(new TextMessage(gson.toJson(data)));
    		
    		log.info("[pong] session_id:" + session.getId());
    		
    		break;
    	}
    	case 2000: 
    	{
    		Long activity_id = NumberUtil.toLong(data.get("activity_id"));
    		if( activities.get(activity_id) == null )
    			activities.put(activity_id, new HashMap<String, Map<String, Object>>());
    		
    		
    		Map<String, Object> client_info = new HashMap<String, Object>();
    		client_info.put("session", session);
    		
    		Long user_id = NumberUtil.toLong(data.get("user_id"));
    		if(user_id != null){
    			client_info.put("user_id", user_id);
    		}    		
    		
    		
    		activities.get(activity_id).put(session.getId(), client_info);
    		
    		break;
    		
    	}
    	case 2002: 
    	{
    		Long activity_id = NumberUtil.toLong(data.get("activity_id"));
    		Map<String, Map<String, Object>> activity = activities.get(activity_id);
    		if( activity != null ){
    			
    			activity.remove(session.getId());
    			
    			if(activity.size() == 0)
    				activities.remove(activity_id);
    		}
    		
    		break;
    	}
    	}
    	/*
    	String payloadMessage = message.getPayload();
         
        for ( int i = 0; i < clients.size(); i++ ) {
        	WebSocketSession client = clients.get(i);
            client.sendMessage(new TextMessage("ECHO : " + payloadMessage));
        }
        */
    }
  
    
    @Override
    public void afterConnectionClosed(WebSocketSession session,
            CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);	
        
        log.info("[Closed] status:" + status + "session_id:" + session.getId());
        
        
        Long user_id = session_uid_map.get(session.getId());
        if( user_id != null ){
        	
        	session_uid_map.remove(session.getId());
	        
	        clients.get(user_id).remove(session.getId());
	        
	        if( clients.get(user_id).size() == 0 )
	        	clients.remove(user_id);
	        
	        log.info("[Closed User] user_id:" + user_id);
        }
        
        
        for(Entry<Long, Map<String, Map<String, Object>>> one : activities.entrySet())
    	{
        	one.getValue().remove(session.getId());
    	}
		
    }
    
   
    public void sendMessage(Long user_id, String message) {
    	
    	
    	if( clients.get(user_id) != null ){
			try {
				
				for(Entry<String, WebSocketSession> entry : clients.get(user_id).entrySet()){					
					entry.getValue().sendMessage( new TextMessage(message) );					
				}	
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    }
    
    
    public void sendMessageToZhuboActivity(Long activity_id, String message) {
    	
    	try {
    		
			Map<String, Map<String, Object>> activity = activities.get(activity_id);
			        
			if(activity != null && activity.size() > 0)
			for(Entry<String, Map<String, Object>> one : activity.entrySet())
	    	{
				((WebSocketSession) one.getValue().get("session")).sendMessage( new TextMessage(message) );
				
	    	}
			
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
    
    
    public void sendMessage(String message) {    	
    	
    	
		try {
			
			for(Entry<Long, Map<String, WebSocketSession>> one : clients.entrySet())
	    	{
	    		for(Entry<String, WebSocketSession> entry : one.getValue().entrySet()){					
					entry.getValue().sendMessage( new TextMessage(message) );					
				}	
	    	}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    
    public Integer getIsConnected(Long user_id) {
    	
    	if( clients.get(user_id) != null )
    		return 1;
    	else
    		return 0;
    	
    }
    
}