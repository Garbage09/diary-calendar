package com.mario.diary.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Pattern;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.util.Patterns;

import com.mario.diary.model.CalendarData;
import com.mario.diary.model.EventData;

public class EventManager {

	private final String AccountSuffix = "@gmail.com";
	private String googleAccount = "";
	public Context ctx;
	private static EventManager instance;

	public static EventManager getInstance() {
		if (instance == null)
			instance = new EventManager();
		return instance;
	}

	public void getAccounts() {
		Pattern emailPattern = Patterns.EMAIL_ADDRESS;
		Account[] accounts = AccountManager.get(ctx).getAccounts();
		for (Account account : accounts) {
			Log.i("test_calendar", "account: " + account.name);
			if (emailPattern.matcher(account.name).matches()) {
				String possibleEmail = account.name;
				if (possibleEmail.endsWith(AccountSuffix)) {
					googleAccount = possibleEmail;
					Log.i("test_calendar", "googleAccount: " + googleAccount);
					return;
				}
			}
		}
	}

	public ArrayList<CalendarData> getCalendarList() {
		Uri uri = Uri.parse(EventConstants.CALENDAR_CONTENT_URI);
		Cursor cur = ctx.getContentResolver().query(
				uri,
				new String[] { EventConstants.CALENDAR_ACCOUNT_NAME,
						EventConstants.CALENDAR_ACCOUNT_TYPE,
						EventConstants.CALENDAR_ACCOUNT_OWNER,
						EventConstants.CALENDAR_ID }, null, null, null);
		cur.moveToFirst();
		String CNames[] = new String[cur.getCount()];
		ArrayList<CalendarData> res = new ArrayList<CalendarData>();
		for (int i = 0; i < CNames.length; i++) {
			com.mario.diary.model.CalendarData calendar = new com.mario.diary.model.CalendarData();
			Log.i("dmquan", "CALENDAR_ACCOUNT_NAME: " + cur.getString(0));
			Log.i("dmquan", "CALENDAR_ACCOUNT_TYPE: " + cur.getString(1));
			Log.i("dmquan", "CALENDAR_ACCOUNT_OWNER: " + cur.getString(2));
			Log.i("dmquan", "CALENDAR_ACCOUNT_ID: " + cur.getString(3));
			calendar.accountName = cur.getString(0);
			calendar.accountType = cur.getString(1);
			calendar.ownerAccount = cur.getString(2);
			calendar.id = cur.getString(3);

			res.add(calendar);
			cur.moveToNext();
		}
		return res;
	}

	public String getCalendarId(String account) {
		Uri uri = Uri.parse(EventConstants.CALENDAR_CONTENT_URI);
		String selection = "(" + EventConstants.CALENDAR_ACCOUNT_NAME + " = ?)";
		String[] selectionArgs = new String[] { account };
		Cursor cur = ctx.getContentResolver().query(uri,
				new String[] { EventConstants.CALENDAR_ID }, selection,
				selectionArgs, null);
		cur.moveToFirst();
		return cur.getString(0);
	}

	public ArrayList<EventData> getEvents(long startTime, long endTime) {
		Log.i("dmquan", "startTimelimit: " + getDate(startTime));
		Log.i("dmquan", "endTimelimit: " + getDate(endTime));
		Uri uri = Uri.parse(EventConstants.EVENT_CONTENT_URI);
		String selection = "(((" + EventConstants.EVENT_DTSTART + ">= "
				+ startTime + ") AND (" + EventConstants.EVENT_DTSTART + "<= "
				+ endTime + ")) OR ((" + EventConstants.EVENT_DTEND + ">= "
				+ startTime + ") AND (" + EventConstants.EVENT_DTEND + "<= "
				+ endTime + ")) OR ((" + EventConstants.EVENT_DTSTART + "< "
				+ startTime + ") AND (" + EventConstants.EVENT_DTEND + "> "
				+ endTime + ")))";
		Cursor cursor = ctx.getContentResolver().query(
				uri,
				new String[] { EventConstants.EVENT_CALENDAR_ID,
						EventConstants.EVENT_TITLE,
						EventConstants.EVENT_DESCRIPTION,
						EventConstants.EVENT_DTSTART,
						EventConstants.EVENT_DTEND,
						EventConstants.EVENT_LOCATION, EventConstants.EVENT_ID,
						EventConstants.EVENT_DURATION,
						EventConstants.EVENT_ALL_DAY,
						EventConstants.EVENT_RRULE, EventConstants.EVENT_RDATE,
						EventConstants.EVENT_TIMEZONE }, selection, null, null);
		cursor.moveToFirst();
		ArrayList<EventData> events = new ArrayList<EventData>();
		for (int i = 0; i < cursor.getCount(); i++) {

			Log.i("dmquan", "calendarId: " + cursor.getString(0));
			Log.i("dmquan", "nameOfEvent: " + cursor.getString(1));

			if (cursor.getString(3) != null) {
				Log.i("dmquan",
						"startDates: "
								+ getDate(Long.parseLong(cursor.getString(3))));
			}
			if (cursor.getString(4) != null) {
				Log.i("dmquan",
						"endDates: "
								+ getDate(Long.parseLong(cursor.getString(4))));
			}
			Log.i("dmquan", "descriptions: " + cursor.getString(2));
			Log.i("dmquan", "location: " + cursor.getString(5));
			Log.i("dmquan", "eventId: " + cursor.getString(6));

			Log.i("dmquan", "duration: " + cursor.getString(7));
			Log.i("dmquan", "allDay: " + cursor.getString(8));
			Log.i("dmquan", "rRule: " + cursor.getString(9));
			Log.i("dmquan", "rDate: " + cursor.getString(10));
			Log.i("dmquan", "timeZone: " + cursor.getString(11));
			Log.i("dmquan",
					"===============================================================");

			EventData event = new EventData();
			if (cursor.getString(3) != null) {
				event.start = Long.parseLong(cursor.getString(3));
			} else {
				event.start = 0;
			}
			if (cursor.getString(4) != null) {
				event.end = Long.parseLong(cursor.getString(4));
			} else {
				event.end = 0;
			}

			event.calendarId = cursor.getString(0);
			event.title = cursor.getString(1);
			event.description = cursor.getString(2);
			event.location = cursor.getString(5);
			event.eventId = Long.parseLong(cursor.getString(6));
			event.duration = cursor.getString(7);
			event.allDay = cursor.getString(8);
			event.rRule = cursor.getString(9);
			event.rDate = cursor.getString(10);
			event.timeZone = cursor.getString(11);
			event = reFormatEvent(event);

			events.add(event);

			cursor.moveToNext();
		}
		cursor.close();
		return events;
	}

	public ArrayList<EventData> getEventAll() {
		Uri uri = Uri.parse(EventConstants.EVENT_CONTENT_URI);
		Cursor cursor = ctx.getContentResolver().query(
				uri,
				new String[] { EventConstants.EVENT_CALENDAR_ID,
						EventConstants.EVENT_TITLE,
						EventConstants.EVENT_DESCRIPTION,
						EventConstants.EVENT_DTSTART,
						EventConstants.EVENT_DTEND,
						EventConstants.EVENT_LOCATION, EventConstants.EVENT_ID,
						EventConstants.EVENT_DURATION,
						EventConstants.EVENT_ALL_DAY,
						EventConstants.EVENT_RRULE, EventConstants.EVENT_RDATE,
						EventConstants.EVENT_TIMEZONE }, null, null, null);
		cursor.moveToFirst();
		String CNames[] = new String[cursor.getCount()];
		ArrayList<EventData> events = new ArrayList<EventData>();
		for (int i = 0; i < CNames.length; i++) {

			Log.i("dmquan", "calendarId: " + cursor.getString(0));
			Log.i("dmquan", "nameOfEvent: " + cursor.getString(1));

			if (cursor.getString(3) != null) {
				Log.i("dmquan",
						"startDates: "
								+ getDate(Long.parseLong(cursor.getString(3))));
			}
			if (cursor.getString(4) != null) {
				Log.i("dmquan",
						"endDates: "
								+ getDate(Long.parseLong(cursor.getString(4))));
			}
			Log.i("dmquan", "descriptions: " + cursor.getString(2));
			Log.i("dmquan", "location: " + cursor.getString(5));
			Log.i("dmquan", "eventId: " + cursor.getString(6));

			Log.i("dmquan", "duration: " + cursor.getString(7));
			Log.i("dmquan", "allDay: " + cursor.getString(8));
			Log.i("dmquan", "rRule: " + cursor.getString(9));
			Log.i("dmquan", "rDate: " + cursor.getString(10));
			Log.i("dmquan", "timeZone: " + cursor.getString(11));
			Log.i("dmquan",
					"===============================================================");

			EventData event = new EventData();
			event.calendarId = cursor.getString(0);
			event.title = cursor.getString(1);
			event.description = cursor.getString(2);
			if (cursor.getString(3) != null) {
				event.start = Long.parseLong(cursor.getString(3));
			} else {
				event.start = 0;
			}
			if (cursor.getString(4) != null) {
				event.end = Long.parseLong(cursor.getString(4));
			} else {
				event.end = 0;
			}
			event.location = cursor.getString(5);
			event.eventId = Long.parseLong(cursor.getString(6));
			event.duration = cursor.getString(7);
			event.allDay = cursor.getString(8);
			event.rRule = cursor.getString(9);
			event.rDate = cursor.getString(10);
			event.timeZone = cursor.getString(11);
			event = reFormatEvent(event);
			events.add(event);
			CNames[i] = cursor.getString(1);
			cursor.moveToNext();
		}
		cursor.close();
		return events;
	}
	
	public void listEventTableColumn() {
		Uri uri = Uri.parse(EventConstants.EVENT_CONTENT_URI);
		Cursor cursor = ctx.getContentResolver().query(
				uri,
				null, "_id=40", null, null);
		cursor.moveToFirst();
		for(int i = 0; i < cursor.getColumnCount();i++){
			Log.d("dmquan",cursor.getColumnName(i) + "=" + cursor.getString(i));
		}
		cursor.close();
	
	}

	public long addCalendarEvent(EventData event) {
		ContentValues values = getContentValue(event);
		Uri uri = ctx.getContentResolver().insert(
				Uri.parse(EventConstants.EVENT_CONTENT_URI), values);
		event.eventId = Long.parseLong(uri.getLastPathSegment());
		return event.eventId;
	}

	public int updateCalendarEvent(EventData event) {
		ContentValues values = getContentValue(event);
		Uri updateUri = ContentUris.withAppendedId(
				Uri.parse(EventConstants.EVENT_CONTENT_URI), event.eventId);
		int rows = ctx.getContentResolver().update(updateUri, values, null,
				null);
		return rows;
	}

	public int deleteCalendarEvent(long eventId) {
		Uri deleteUri = null;
		deleteUri = ContentUris.withAppendedId(
				Uri.parse(EventConstants.EVENT_CONTENT_URI), eventId);
		int rows = ctx.getContentResolver().delete(deleteUri, null, null);
		return rows;
	}

	private EventData reFormatEvent(EventData event) {

		return event;
	}

	private ContentValues getContentValue(EventData event) {
		ContentValues values = new ContentValues();
		values.put(EventConstants.EVENT_DTSTART, event.start);
		values.put(EventConstants.EVENT_TITLE, event.title);
		values.put(EventConstants.EVENT_DESCRIPTION, event.description);
		values.put(EventConstants.EVENT_CALENDAR_ID, event.calendarId);
		values.put(EventConstants.EVENT_LOCATION, event.location);
		values.put(EventConstants.EVENT_ALL_DAY, event.allDay);
		values.put(EventConstants.EVENT_TIMEZONE, event.timeZone);

		if (event.duration == null) {
			values.put(EventConstants.EVENT_DTEND, event.end);
		} else {
			if (event.allDay.equalsIgnoreCase("0"))
				values.put(EventConstants.EVENT_DURATION, event.duration);
			if (event.rRule != null)
				values.put(EventConstants.EVENT_RRULE, event.rRule);
			else if (event.rDate != null)
				values.put(EventConstants.EVENT_RDATE, event.rDate);
		}
		return values;
	}

	@SuppressLint("SimpleDateFormat")
	public String getDate(long milliSeconds) {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"dd/MM/yyyy hh:mm:ss a");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliSeconds);
		return formatter.format(calendar.getTime());
	}

	public void init(Context context) {
		ctx = context;
	}
}
