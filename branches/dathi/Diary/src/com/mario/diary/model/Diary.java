package com.mario.diary.model;

public class Diary {
	private int iDay;
	private String strDayOfWeek;
	private String strContentDiary;

	public Diary() {
	}

	public Diary(int day, String dow, String diary) {
		// TODO Auto-generated constructor stub
		this.iDay = day;
		this.strDayOfWeek = dow;
		this.strContentDiary = diary;
	}

	public int getDay() {
		return iDay;
	}

	public void setDay(int strDay) {
		this.iDay = strDay;
	}

	public String getDayOfWeek() {
		return strDayOfWeek;
	}

	public void setDayOfWeek(String strDayOfWeek) {
		this.strDayOfWeek = strDayOfWeek;
	}

	public String getStrContentDiary() {
		return strContentDiary;
	}

	public void setStrContentDiary(String strContentDiary) {
		this.strContentDiary = strContentDiary;
	}

}
