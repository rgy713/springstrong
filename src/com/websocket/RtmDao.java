package com.websocket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.xspeeder.dao.BaseDao;

public class RtmDao extends BaseDao {

	public List<Map<String, Object>> getSession( Long uid ) 
	{
		String sql = 
				"SELECT "
				+ " 	uid,"
				+ " 	server,"
				+ " 	session_id "
				+ " FROM tbl_websocket_sessions "
				+ " WHERE uid = ?";
		
		try{
			return this.jdbcTemplate.queryForList(sql, uid);
		}
		catch(Exception e) {
			return null;
		}
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void addNewSession( String session_id, Long uid, String server ) 
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("uid", uid);
		params.put("server", server);
		params.put("session_id", session_id);
		
		this.insertNoId("tbl_websocket_sessions", params);
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void removeSession( Long uid, String server, String session_id ) 
	{
		String sql = "DELETE FROM tbl_websocket_sessions WHERE uid = ? AND server = ? AND session_id = ?";
		this.jdbcTemplate.update(sql, uid, server, session_id);
	}
}
