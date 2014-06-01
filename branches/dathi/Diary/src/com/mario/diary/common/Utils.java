package com.mario.diary.common;

import java.util.Calendar;
import java.util.Date;

public class Utils {

	public static Date getLastMonthLastDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);

		int max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, max);

		return calendar.getTime();
	}
	
	public static int getLastDayInMonth(Calendar calendar, int dayOfYear) {
		calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);	    
	    int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return maxDay;
	}

	public static int getNextDayOfWeek() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_WEEK, 1);
		int dow = c.get(Calendar.DAY_OF_WEEK);
		return dow;
	}
	
	public static int getDayOfMonthFromDayOfYear(Calendar calendar, int dayOfYear) {
	    calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);
	    int dom = calendar.get(Calendar.DAY_OF_MONTH);
		return dom;
	}
	
	public static int getMonthFromDayOfYear(Calendar calendar, int dayOfYear) {
	    calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);
	    int month = calendar.get(Calendar.MONTH);
		return month;
	}

	public static String getDayOfWeekFromDayOfYear(Calendar calendar, int dayOfYear) {
	    calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);
	    int dow = calendar.get(Calendar.DAY_OF_WEEK);
		return getNameDayOfWeek(dow);
	}
	
	private static String getNameDayOfWeek(int dow) {
		String result = "";
		switch (dow) {
		case 1:
			result = "Sun";
			break;
		case 2:
			result = "Mon";
			break;
		case 3:
			result = "Tue";
			break;
		case 4:
			result = "Wed";
			break;
		case 5:
			result = "Thu";
			break;
		case 6:
			result = "Fri";
			break;
		case 7:
			result = "Sat";
			break;
		}
		return result;
	}
}
