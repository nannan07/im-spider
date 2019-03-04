package com.allmsi.spider.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);
	}

	public static String stringFormat(String dateString) {
		dateString = dateString.substring(0, 4) + "-" + dateString.substring(4, 6) + "-" + dateString.substring(6, 8);
		return dateString;
	}

	public static Date stringToDate(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String getPreDate(String dateString) {
		Date date = stringToDate(dateString);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return dateToString(date);
	}

	public static String getPreDate(Date date) {
		return getPreDate(dateToString(date));
	}

	public static String getPreDates(String dateString, int num) {
		Date date = stringToDate(dateString);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -(num - 1));
		date = calendar.getTime();
		return dateToString(date);
	}

	public static Long getCurrentTimeMillis() {
		long time = System.currentTimeMillis();
		return time;
	}

	public static void main(String[] args) {
		System.out.println(getPreDates("20171205", 1));
	}

	/**
	 * 功能描述：返回小
	 *
	 * @param date 日期
	 * @return 返回小时
	 */
	public static int getHour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
}
