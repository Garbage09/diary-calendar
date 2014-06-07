package com.mario.diary.common;

import java.util.TimeZone;

import android.annotation.SuppressLint;
import android.provider.CalendarContract.Calendars;
import android.provider.CalendarContract.Events;

@SuppressLint("NewApi")
public class EventConstants {
	
	public static final String CALENDAR_NAME = "diary_calendar";
	
	// Column name of Device Calendar
	public static final String CALENDAR_CONTENT_URI = android.os.Build.VERSION.SDK_INT >= 14 ? Calendars.CONTENT_URI.toString() : "content://com.android.calendar/calendars";
	//public static final String CALENDAR_ACCOUNT_NAME = android.os.Build.VERSION.SDK_INT >= 14 ? Calendars.ACCOUNT_NAME : "account_name";
	public static final String CALENDAR_ACCOUNT_NAME = android.os.Build.VERSION.SDK_INT >= 14 ? Calendars.ACCOUNT_NAME : "name";
	public static final String CALENDAR_ACCOUNT_TYPE = android.os.Build.VERSION.SDK_INT >= 14 ? Calendars.ACCOUNT_TYPE : "_sync_account_type";
	public static final String CALENDAR_ACCOUNT_OWNER = android.os.Build.VERSION.SDK_INT >= 14 ? Calendars.OWNER_ACCOUNT : "ownerAccount";
	public static final String CALENDAR_ID = android.os.Build.VERSION.SDK_INT >= 14 ? Calendars._ID : "_id";
	
	public static final String EVENT_CONTENT_URI = android.os.Build.VERSION.SDK_INT >= 14 ? Events.CONTENT_URI.toString() : "content://com.android.calendar/events";
	public static final String EVENT_CALENDAR_ID = android.os.Build.VERSION.SDK_INT >= 14 ? Events.CALENDAR_ID : "calendar_id";
	public static final String EVENT_TITLE = android.os.Build.VERSION.SDK_INT >= 14 ? Events.TITLE : "title";
	public static final String EVENT_DESCRIPTION = android.os.Build.VERSION.SDK_INT >= 14 ? Events.DESCRIPTION : "description";
	public static final String EVENT_DTSTART = android.os.Build.VERSION.SDK_INT >= 14 ? Events.DTSTART : "dtstart";
	public static final String EVENT_DTEND = android.os.Build.VERSION.SDK_INT >= 14 ? Events.DTEND : "dtend";
	public static final String EVENT_LOCATION = android.os.Build.VERSION.SDK_INT >= 14 ? Events.EVENT_LOCATION : "eventLocation";
	public static final String EVENT_ID = android.os.Build.VERSION.SDK_INT >= 14 ? Events._ID : "_id";
	public static final String EVENT_DURATION = android.os.Build.VERSION.SDK_INT >= 14 ? Events.DURATION : "duration";
	public static final String EVENT_ALL_DAY = android.os.Build.VERSION.SDK_INT >= 14 ? Events.ALL_DAY : "allDay";
	public static final String EVENT_RRULE = android.os.Build.VERSION.SDK_INT >= 14 ? Events.RRULE : "rrule";
	public static final String EVENT_RDATE = android.os.Build.VERSION.SDK_INT >= 14 ? Events.RDATE : "rdate";
	public static final String EVENT_TIMEZONE = android.os.Build.VERSION.SDK_INT >= 14 ? Events.EVENT_TIMEZONE : "eventTimezone";
	
	// Current TimeZonf of Device
	public static final String CURRENT_TIMEZONE = TimeZone.getDefault().getID();
}
