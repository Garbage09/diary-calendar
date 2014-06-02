package com.mario.diary.common;

public class Utils {
	
	public static String getNameDayOfWeek(int dow) {
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
