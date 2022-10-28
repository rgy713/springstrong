package com.websocket.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.websocket.WebsocketEndPoint;
import com.xspeeder.common.util.ContextUtils;
import com.xspeeder.common.util.JsonUtil;
import com.xspeeder.common.util.NumberUtil;

public class WebsocketUtil {
	
	public static final Integer CONST_REALTIME_CHATTING				= 1;
	public static final Integer CONST_REALTIME_CHATTING_READING		= 2;
	public static final Integer CONST_REALTIME_COMMENT				= 3;
	public static final Integer CONST_REALTIME_AUDITION_GIFT		= 4;
	public static final Integer CONST_REALTIME_CASTING				= 5;
	public static final Integer CONST_REALTIME_SYSMSG				= 6;
	
	public static final Integer CONST_REALTIME_ZHUBO_ADD_ACTIVITY	= 7;
	public static final Integer CONST_REALTIME_ZHUBO_BEFORE_STARTING_ACTIVITY = 8;
	public static final Integer CONST_REALTIME_ZHUBO_STARTING_ACTIVITY = 9;
	public static final Integer CONST_REALTIME_ZHUBO_ENTER_TO_ACTIVITY = 10;
	public static final Integer CONST_REALTIME_ZHUBO_COMMENT	= 11;
	public static final Integer CONST_REALTIME_ZHUBO_FAVOURITE	= 12;
	public static final Integer CONST_REALTIME_ZHUBO_SEND_GIFT	= 13;
	public static final Integer CONST_REALTIME_ZHUBO_FINISH_COUNTINOUS_GIFT	= 14;
	public static final Integer CONST_REALTIME_ZHUBO_END_ACTIVITY = 15;
	
	
	public static WebsocketEndPoint getWebsocketEndPoint(){
		ServletContext sc = ContextUtils.servletContext();
		WebApplicationContext wactx = ContextUtils.getWebAppCtx(sc);
		
		//return (WebsocketEndPoint)ContextUtils.appContext().getBean("websocketHandler");
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
    	
    	
    	WebsocketEndPoint wsEndPoint = WebsocketUtil.getWebsocketEndPoint();
    	
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
    		Integer state
    		)
    {
    	
		WebsocketEndPoint wsEndPoint = WebsocketUtil.getWebsocketEndPoint();
		
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("chatting_id", chatting_id);
		data.put("chatting_kind", chatting_kind);
		data.put("content", content);
		if( file_name != null) data.put("file_name", file_name);
		data.put("sender", sender);
		data.put("receiver", receiver);
		data.put("send_date", send_date);
		data.put("state", state);
		
		
		String jsonResult = WebsocketUtil.makeJsonResult(CONST_REALTIME_CHATTING, data);
		
		
		wsEndPoint.sendMessage(receiver, jsonResult);
    }
    
    
    public static void sendChattingReading(
    		Long sender,
    		Long receiver,
    		Integer count
    		)
    {
    	
    	
		WebsocketEndPoint wsEndPoint = WebsocketUtil.getWebsocketEndPoint();
		
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("sender", sender);
		data.put("count", count);
		
		
		String jsonResult = WebsocketUtil.makeJsonResult(CONST_REALTIME_CHATTING_READING, data);
		
		
		wsEndPoint.sendMessage(receiver, jsonResult);
    }

    
    public static void sendAuditionGift(
    		Long audition_gift_id,
    		Long audition_id,
    		Long sender_id,
    		String sender_alias,
    		String sender_avatar,
    		Long gift_id,
    		String gift_name,
    		Integer count,
    		Date send_time
    		)
    {
    	
    	
		WebsocketEndPoint wsEndPoint = WebsocketUtil.getWebsocketEndPoint();
		
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("audition_gift_id", audition_gift_id);
		data.put("audition_id", audition_id);
		data.put("sender_id", sender_id);
		data.put("sender_alias", sender_alias);
		data.put("sender_avatar", sender_avatar);
		data.put("gift_id", gift_id);
		data.put("gift_name", gift_name);
		data.put("count", count);
		data.put("send_time", send_time);
		
		
		String jsonResult = WebsocketUtil.makeJsonResult(CONST_REALTIME_AUDITION_GIFT, data);
		
		
		wsEndPoint.sendMessage(jsonResult);
    			
    }
    
    
    public static void sendComment(
    		Integer comment_kind,
    		Long comment_id,
    		Long item_id,
    		String content,
    		Long commenter_id,
    		String commenter_alias,
    		String commenter_avatar,
    		Date commented_time
    		)
    {
    	
    	
		WebsocketEndPoint wsEndPoint = WebsocketUtil.getWebsocketEndPoint();
		
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("comment_kind", comment_kind);
		data.put("comment_id", comment_id);
		data.put("item_id", item_id);
		data.put("content", content);
		data.put("commenter_id", commenter_id);
		data.put("commenter_alias", commenter_alias);
		data.put("commenter_avatar", commenter_avatar);
		data.put("commented_time", commented_time);
		
		
		String jsonResult = WebsocketUtil.makeJsonResult(CONST_REALTIME_COMMENT, data);
		
		
		wsEndPoint.sendMessage(jsonResult);
    			
    }
    
   
    public static void sendCasting(
    		Long media_id,
    		String media_title,
    		Long audition_id,
    		Long maker_id,
    		String maker_alias,
    		String maker_avatar,
    		Date upload_date
    		)
    {
    	
    
		WebsocketEndPoint wsEndPoint = WebsocketUtil.getWebsocketEndPoint();
		
	
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("media_id", media_id);
		data.put("media_title", media_title);
		data.put("audition_id", audition_id);
		data.put("maker_id", maker_id);
		data.put("maker_alias", maker_alias);
		data.put("maker_avatar", maker_avatar);
		data.put("upload_date", upload_date);
		
		
		String jsonResult = WebsocketUtil.makeJsonResult(CONST_REALTIME_CASTING, data);
		
		
		wsEndPoint.sendMessage(jsonResult);
    			
    }
    
    
    public static void sendSysmsg(
    		Long sysmsg_id,
    		Integer sysmsg_kind,
    		String message,
    		Long sender,
    		Object sender_avatar,
    		Long receiver,
    		Long media_id,
    		Date send_date,
    		Integer state
    		)
    {
    	
		WebsocketEndPoint wsEndPoint = WebsocketUtil.getWebsocketEndPoint();
		
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("sysmsg_id", sysmsg_id);
		data.put("sysmsg_kind", sysmsg_kind);
		data.put("message", message);
		data.put("sender", sender);
		data.put("sender_avatar", sender_avatar);
		data.put("receiver", receiver);
		data.put("media_id", media_id);
		data.put("send_date", send_date);
		data.put("state", state);
		
		
		String jsonResult = WebsocketUtil.makeJsonResult(CONST_REALTIME_SYSMSG, data);
		
		
		wsEndPoint.sendMessage(receiver, jsonResult);
    }
    
   
    public static void sendAddZhuboActivity(
    		List<Map<String, Object>> subscribers,
    		Long id,
    		Long user_id,
    		String user_intro,
    		String title,
    		String summary,
    		String banner,
    		String h5link,
    		Float price1,
    		Float price2,
    		Float price3,
    		Float price,
    		Date create_time,
    		Date promise_time,
    		String state,
    		String push_url,
    		String live_url,
    		String hls_live_url,
    		String play_url,
    		String hls_play_url,
    		String alias,
    		Integer applicant_count,
    		Integer participant_count,
    		Integer favourite_count
    		)
    {
    	
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", id);
		data.put("user_id", user_id);
		data.put("user_intro", user_intro);
		data.put("title", title);
		data.put("summary", summary);
		data.put("banner", banner);
		data.put("h5link", h5link);
		data.put("price1", price1);
		data.put("price2", price2);
		data.put("price3", price3);
		data.put("price", price);
		data.put("create_time", create_time);
		data.put("promise_time", promise_time);
		data.put("state", state);
		data.put("push_url", push_url);
		data.put("live_url", live_url);
		data.put("hls_live_url", hls_live_url);
		data.put("play_url", play_url);
		data.put("hls_play_url", hls_play_url);
		data.put("alias", alias);
		data.put("applicant_count", applicant_count);
		data.put("participant_count", participant_count);
		data.put("favourite_count", favourite_count);
		
		
		String jsonResult = WebsocketUtil.makeJsonResult(CONST_REALTIME_ZHUBO_ADD_ACTIVITY, data);
		
		
		WebsocketUtil.sendMessageToZhuboSubscribers(id, subscribers, jsonResult);
		
    }
    
    
    public static void sendAddZhuboActivity(
    		List<Map<String, Object>> subscribers,
    		Map<String, Object> data
    		)
    {
    	
		
		String jsonResult = WebsocketUtil.makeJsonResult(CONST_REALTIME_ZHUBO_ADD_ACTIVITY, data);
		
		
		WebsocketUtil.sendMessageToZhuboSubscribers(NumberUtil.toLong(data.get("id")), subscribers, jsonResult);
		
    }
    
    
    public static void sendBeforeStartingZhuboActivity(
    		List<Map<String, Object>> subscribers,
    		Long id,
    		Long user_id,
    		String user_intro,
    		String title,
    		String summary,
    		String banner,
    		String h5link,
    		Float price1,
    		Float price2,
    		Float price3,
    		Float price,
    		Date create_time,
    		Date promise_time,
    		String state,
    		String push_url,
    		String live_url,
    		String hls_live_url,
    		String play_url,
    		String hls_play_url,
    		String alias,
    		Integer applicant_count,
    		Integer participant_count,
    		Integer favourite_count
    		)
    {
    	
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", id);
		data.put("user_id", user_id);
		data.put("user_intro", user_intro);
		data.put("title", title);
		data.put("summary", summary);
		data.put("banner", banner);
		data.put("h5link", h5link);
		data.put("price1", price1);
		data.put("price2", price2);
		data.put("price3", price3);
		data.put("price", price);
		data.put("create_time", create_time);
		data.put("promise_time", promise_time);
		data.put("state", state);
		data.put("push_url", push_url);
		data.put("live_url", live_url);
		data.put("hls_live_url", hls_live_url);
		data.put("play_url", play_url);
		data.put("hls_play_url", hls_play_url);
		data.put("alias", alias);
		data.put("applicant_count", applicant_count);
		data.put("participant_count", participant_count);
		data.put("favourite_count", favourite_count);
		
		
		String jsonResult = WebsocketUtil.makeJsonResult(CONST_REALTIME_ZHUBO_BEFORE_STARTING_ACTIVITY, data);
		
		
		WebsocketUtil.sendMessageToZhuboSubscribers(id, subscribers, jsonResult);
		
    }
    
    
    public static void sendBeforeStartingZhuboActivity(
    		List<Map<String, Object>> subscribers,
    		Map<String, Object> data
    		)
    {
    	
	
		String jsonResult = WebsocketUtil.makeJsonResult(CONST_REALTIME_ZHUBO_BEFORE_STARTING_ACTIVITY, data);
		
	
		WebsocketUtil.sendMessageToZhuboSubscribers(NumberUtil.toLong(data.get("id")), subscribers, jsonResult);
		
    }
    
    
    public static void sendBeforeStartingZhuboActivity(
    		List<Map<String, Object>> subscribers,
    		Map<String, Object> data,
    		WebsocketEndPoint wsEndPoint
    		)
    {
    	
		
		String jsonResult = WebsocketUtil.makeJsonResult(CONST_REALTIME_ZHUBO_BEFORE_STARTING_ACTIVITY, data);
		
		
		WebsocketUtil.sendMessageToZhuboSubscribers(subscribers, jsonResult, wsEndPoint);
		
    }
    
    
    public static void sendStartingZhuboActivity(
    		List<Map<String, Object>> subscribers,
    		Long id,
    		Long user_id,
    		String user_intro,
    		String title,
    		String summary,
    		String banner,
    		String h5link,
    		Float price1,
    		Float price2,
    		Float price3,
    		Float price,
    		Date create_time,
    		Date promise_time,
    		String state,
    		String push_url,
    		String live_url,
    		String hls_live_url,
    		String play_url,
    		String hls_play_url,
    		String alias,
    		Integer applicant_count,
    		Integer participant_count,
    		Integer favourite_count
    		)
    {
    	
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", id);
		data.put("user_id", user_id);
		data.put("user_intro", user_intro);
		data.put("title", title);
		data.put("summary", summary);
		data.put("banner", banner);
		data.put("h5link", h5link);
		data.put("price1", price1);
		data.put("price2", price2);
		data.put("price3", price3);
		data.put("price", price);
		data.put("create_time", create_time);
		data.put("promise_time", promise_time);
		data.put("state", state);
		data.put("push_url", push_url);
		data.put("live_url", live_url);
		data.put("hls_live_url", hls_live_url);
		data.put("play_url", play_url);
		data.put("hls_play_url", hls_play_url);
		data.put("alias", alias);
		data.put("applicant_count", applicant_count);
		data.put("participant_count", participant_count);
		data.put("favourite_count", favourite_count);
		
		
		String jsonResult = WebsocketUtil.makeJsonResult(CONST_REALTIME_ZHUBO_STARTING_ACTIVITY, data);
		
		
		WebsocketUtil.sendMessageToZhuboSubscribers(id, subscribers, jsonResult);
		
    }
    
   
    public static void sendStartingZhuboActivity(
    		List<Map<String, Object>> subscribers,
    		Map<String, Object> data
    		)
    {
    	
		
		String jsonResult = WebsocketUtil.makeJsonResult(CONST_REALTIME_ZHUBO_STARTING_ACTIVITY, data);
		
		
		WebsocketUtil.sendMessageToZhuboSubscribers(NumberUtil.toLong(data.get("id")), subscribers, jsonResult);
		
    }    
    
    public static void sendMessageToZhuboSubscribers(
    		Long activity_id,
    		List<Map<String, Object>> subscribers,
    		String msg
    		)
    {
    	
		WebsocketEndPoint wsEndPoint = WebsocketUtil.getWebsocketEndPoint();
		
		Iterator<Map<String, Object>> iter = subscribers.iterator();
		while(iter.hasNext())
		{
			Map<String, Object> one = iter.next();
			Long user_id = NumberUtil.toLong(one.get("id"));

			
			wsEndPoint.sendMessage(user_id, msg);
		}
		
		
		wsEndPoint.sendMessageToZhuboActivity(activity_id, msg);
		
    }
    
    
    public static void sendMessageToZhuboSubscribers(
    		List<Map<String, Object>> subscribers,
    		String msg,
    		WebsocketEndPoint wsEndPoint
    		)
    {
		
		Iterator<Map<String, Object>> iter = subscribers.iterator();
		while(iter.hasNext())
		{
			Map<String, Object> one = iter.next();
			Long user_id = NumberUtil.toLong(one.get("id"));

			
			wsEndPoint.sendMessage(user_id, msg);
		}
		
    }
    
   
    public static void sendEnterToZhuboActivity(
    		Long activity_id,
    		Long user_id,
    		String alias,
    		String avatar
    		)
    {
    	
		WebsocketEndPoint wsEndPoint = WebsocketUtil.getWebsocketEndPoint();
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", activity_id);
		data.put("user_id", user_id);
		data.put("alias", alias);
		data.put("avatar", avatar);
		
		
		String jsonResult = WebsocketUtil.makeJsonResult(CONST_REALTIME_ZHUBO_ENTER_TO_ACTIVITY, data);
		
		
		wsEndPoint.sendMessageToZhuboActivity(activity_id, jsonResult);
    }
    
    
    public static void sendCommentForZhubo(
    		Long activity_id,
    		Long user_id,
    		String alias,
    		String avatar,
    		String content
    		)
    {
    
		WebsocketEndPoint wsEndPoint = WebsocketUtil.getWebsocketEndPoint();
		
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", activity_id);
		data.put("user_id", user_id);
		data.put("alias", alias);
		data.put("avatar", avatar);
		data.put("content", content);
		
		
		String jsonResult = WebsocketUtil.makeJsonResult(CONST_REALTIME_ZHUBO_COMMENT, data);
		
		
		wsEndPoint.sendMessageToZhuboActivity(activity_id, jsonResult);
    }
    
    
    public static void sendFavouriteForZhubo(
    		Long activity_id,
    		Long user_id,
    		String alias,
    		String avatar
    		)
    {
    	
		WebsocketEndPoint wsEndPoint = WebsocketUtil.getWebsocketEndPoint();
		
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", activity_id);
		data.put("user_id", user_id);
		data.put("alias", alias);
		data.put("avatar", avatar);
		
		
		String jsonResult = WebsocketUtil.makeJsonResult(CONST_REALTIME_ZHUBO_FAVOURITE, data);
		
		
		wsEndPoint.sendMessageToZhuboActivity(activity_id, jsonResult);
    }
    
   
    public static void sendGiftForZhubo(
    		Long activity_id,
    		Long user_id,
    		String alias,
    		String avatar,
    		String account_type,
    		Integer gift_id,
    		String gift_name,
    		String is_continous,
    		Integer pengdou,
    		Integer with_charge
    		)
    {
    
		WebsocketEndPoint wsEndPoint = WebsocketUtil.getWebsocketEndPoint();
		
	
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", activity_id);
		data.put("user_id", user_id);
		data.put("alias", alias);
		data.put("avatar", avatar);
		data.put("account_type", account_type);
		data.put("gift_id", gift_id);
		data.put("gift_name", gift_name);
		data.put("is_continous", is_continous);
		data.put("pengdou", pengdou);
		data.put("with_charge", with_charge);
		
	
		String jsonResult = WebsocketUtil.makeJsonResult(CONST_REALTIME_ZHUBO_SEND_GIFT, data);
		

		wsEndPoint.sendMessageToZhuboActivity(activity_id, jsonResult);
    }
    

    public static void SendFinishContinousGiftForZhubo(
    		Long activity_id,
    		Long user_id,
    		String alias,
    		String avatar,
    		String account_type,
    		Integer gift_id,
    		String gift_name,
    		Integer gift_count
    		)
    {
    	
		WebsocketEndPoint wsEndPoint = WebsocketUtil.getWebsocketEndPoint();
		
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", activity_id);
		data.put("user_id", user_id);
		data.put("alias", alias);
		data.put("avatar", avatar);
		data.put("account_type", account_type);
		data.put("gift_id", gift_id);
		data.put("gift_name", gift_name);
		data.put("gift_count", gift_count);
		
		
		String jsonResult = WebsocketUtil.makeJsonResult(CONST_REALTIME_ZHUBO_FINISH_COUNTINOUS_GIFT, data);
		
		
		wsEndPoint.sendMessageToZhuboActivity(activity_id, jsonResult);
    }
    
    
    public static void sendEndZhuboActivity(
    		Long activity_id,
    		String start_time,
    		String end_time
    		)
    {
    	
		WebsocketEndPoint wsEndPoint = WebsocketUtil.getWebsocketEndPoint();
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", activity_id);
		data.put("start_time", start_time);
		data.put("end_time", end_time);
		
		
		String jsonResult = WebsocketUtil.makeJsonResult(CONST_REALTIME_ZHUBO_END_ACTIVITY, data);
		
		
		wsEndPoint.sendMessageToZhuboActivity(activity_id, jsonResult);
    }
    
}
