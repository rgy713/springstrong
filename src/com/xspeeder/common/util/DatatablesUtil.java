package com.xspeeder.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

public class DatatablesUtil {


	public static List<DatatablesOrder> parseOrder(HttpServletRequest req)
	{
		List<DatatablesOrder> orders = new ArrayList<DatatablesOrder>();
		int i = 0;
		while(true)
		{
			String col_idx = req.getParameter("order[" + i + "][column]");
			
			if( col_idx == null )
				break;
			
			String col_name = req.getParameter("columns[" + col_idx + "][name]");
			String dir = req.getParameter("order[" + i + "][dir]");
			
			DatatablesOrder one = new DatatablesOrder();
			one.col_idx = Integer.valueOf(col_idx);
			one.col_name = col_name;
			one.dir = dir;
			
			orders.add(one);
			
			i++;
		}
		
		return orders;
	}
	
	public static String makeJsonResult( Long user_count, @SuppressWarnings("rawtypes") List<Map<String, Object>> user_list, int draw, int start )
	{
		if( !ListUtil.isEmpty(user_list) )
		{
			int i = 1;
			Iterator<Map<String, Object>> iter = user_list.iterator();
			while(iter.hasNext())
			{
				Map<String, Object> one = iter.next();
				one.put("row_no", start + i);
				i++;
			}
		}
		
		Gson gson = JsonUtil.slashedGson();
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("draw", draw + 1);
		params.put("recordsFiltered", user_count);
		params.put("recordsTotal", user_count);
		params.put("data", user_list);
		
		return gson.toJson(params);
	}
}
