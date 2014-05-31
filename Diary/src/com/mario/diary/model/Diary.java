package com.mario.diary.model;

public class Diary {
	private int iDay;
	private int iDayOfWeek;

	public Diary() {
	}

	public Diary(int day, int dow) {
		// TODO Auto-generated constructor stub
		this.iDay = day;
		this.iDayOfWeek = dow;
	}

	public int getDay() {
		return iDay;
	}

	public void setDay(int strDay) {
		this.iDay = strDay;
	}

	public int getDayOfWeek() {
		return iDayOfWeek;
	}

	public void setDayOfWeek(int strDayOfWeek) {
		this.iDayOfWeek = strDayOfWeek;
	}

}
