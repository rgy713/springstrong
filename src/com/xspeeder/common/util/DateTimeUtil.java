/**
 * Copyright University Development Team
 * All rights reserved.
 * 
 */

package com.xspeeder.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;


public class DateTimeUtil {
	
	public static Date now() {
		GregorianCalendar calendar = new GregorianCalendar();
		return calendar.getTime();
	}
	
	public static Date utcNow() {
		
		GregorianCalendar calendar = new GregorianCalendar();
		Date now = calendar.getTime();
		
		DateFormat converter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		converter.setTimeZone(TimeZone.getTimeZone("UTC"));
		String f = converter.format(now);
		
		return DateTimeUtil.castYYYYMMDDHHmmssToDate(f);
	}
	
	public static String format( Date date, String fmt ) {
		
		if( date == null )
			return null;
		
        DateFormat df = new SimpleDateFormat(fmt);
        return df.format(date);
    }
	
	public static Date addMonth(Date date, int val) {
		return addDateTime(date, Calendar.MONTH, val);
	}
	
	public static Date addDay(Date date, int val) {
		return addDateTime(date, Calendar.DATE, val);
	}
	
	public static Date addHour(Date date, int val) {
		return addDateTime(date, Calendar.HOUR, val);
	}
	
	public static Date addMinute(Date date, int val) {
		return addDateTime(date, Calendar.MINUTE, val);
	}
	
	public static Date addSecond(Date date, int val) {
		return addDateTime(date, Calendar.SECOND, val);
	}
	
	private static Date addDateTime(Date date, int field, int val) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(field, val);
		
		return calendar.getTime();
	}

	public static String castDateToYYYYMMDD() {
		String ret = castDateToYYYYMMDD(new Date());
		return ret;
	}

	public static String castDateToYYYYMMDD(Date date) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		
		String ret = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		return ret;
	}
	
	public static String castDateToYYYYMMDDHHmmss(Date date) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		
		String ret = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
		return ret;
	}

	public static Date castYYYYMMDDToDate() {
		Date ret = castYYYYMMDDToDate(castDateToYYYYMMDD());
		return ret;
	}

	public static Date castYYYYMMDDToDate(String yyyyMMdd) {
		Date date = null;
		try {
			if (!StringUtil.isEmpty(yyyyMMdd)) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				date = format.parse(yyyyMMdd);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}
	
	public static Date castYYYYMMDDHHmmssToDate(String yyyyMMddHHmmss) {
		Date date = null;
		try {
			if (!StringUtil.isEmpty(yyyyMMddHHmmss)) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				date = format.parse(yyyyMMddHHmmss);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}
	
	public static Date castYYYYMMDDHHmmToDate(String yyyyMMddHHmm) {
		Date date = null;
		try {
			if (!StringUtil.isEmpty(yyyyMMddHHmm)) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				date = format.parse(yyyyMMddHHmm);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}
	
	public static Date beginOfMonth(Date date) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		
		GregorianCalendar begin = new GregorianCalendar(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), 1);
		return begin.getTime();
	}
	
	public static Date endOfMonth(Date date) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		int maxday = gc.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		GregorianCalendar end = new GregorianCalendar(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), maxday);
		return end.getTime();
	}
	
	public static Date localtime( Date utctime, long offset )
	{
		Date local_time = new Date();
		local_time.setTime(utctime.getTime() - offset);
		
		return local_time;
	}
	
	public static TimeAgoUnit timeAgo(Date target) {
		
		long diff = DateTimeUtil.utcNow().getTime() - target.getTime();

		double seconds = Math.abs(diff) / 1000;
		double minutes = seconds / 60;
		double hours = minutes / 60;
		double days = hours / 24;
		double years = days / 365;

		TimeAgoUnit words;

		if (seconds < 45) {
			words = new TimeAgoUnit("seconds", seconds);
		} else if (seconds < 90) {
			words = new TimeAgoUnit("minute", 1);
		} else if (minutes < 45) {
			words = new TimeAgoUnit("minutes", Math.round(minutes));
		} else if (minutes < 90) {
			words = new TimeAgoUnit("hour", 1);
		} else if (hours < 24) {
			words = new TimeAgoUnit("hours", Math.round(hours));
		} else if (hours < 42) {
			words = new TimeAgoUnit("day", 1);
		} else if (days < 30) {
			words = new TimeAgoUnit("days", Math.round(days));
		} else if (days < 45) {
			words = new TimeAgoUnit("month", 1);
		} else if (days < 365) {
			words = new TimeAgoUnit("months", Math.round(days / 30));
		} else if (years < 1.5) {
			words = new TimeAgoUnit("year", 1);
		} else {
			words = new TimeAgoUnit("years", Math.round(years));
		}

		return words;
	}

	public static String castDateToYYYYMM(Date date) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		
		String ret = new SimpleDateFormat("yyyy-MM").format(calendar.getTime());
		return ret;
	}
	
	public static String getLocalTimeZone()
	{
		String local_time_zone="";
		String time_zone_decimal = "";
		int temp=0;		
		
		Long time_zone = 0 - (Long)ContextUtils.session().getAttribute(""/*Const.SESS_TIMEOFFSET*/)/60/60/1000;
		temp=(int)((Math.abs(time_zone)-(int)(long)Math.abs(time_zone))*100);
		time_zone_decimal=String.valueOf(temp);
		if (temp<10) time_zone_decimal = "0" + time_zone_decimal;
		
		local_time_zone=String.valueOf((int) (long) time_zone);
		
		if(time_zone>=0) local_time_zone= "+" + local_time_zone; 
			
		local_time_zone=local_time_zone + ":" + time_zone_decimal;
		
		return local_time_zone;
	}
}
