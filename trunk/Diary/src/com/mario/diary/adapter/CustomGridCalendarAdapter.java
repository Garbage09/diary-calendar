package com.mario.diary.adapter;

import hirondelle.date4j.DateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimeZone;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import com.mario.diary.R;
import com.mario.diary.common.EventManager;
import com.mario.diary.model.EventData;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidGridAdapter;

public class CustomGridCalendarAdapter extends CaldroidGridAdapter {
	private ArrayList<EventData> eventList;
	public CustomGridCalendarAdapter(Context context, int month, int year,
			HashMap<String, Object> caldroidData,
			HashMap<String, Object> extraData) {
		super(context, month, year, caldroidData, extraData);
		long startTime = datetimeList.get(0).getMilliseconds(TimeZone.getDefault());
		long endTime = datetimeList.get(datetimeList.size() - 1).getMilliseconds(TimeZone.getDefault());
		this.eventList = EventManager.getInstance().getEvents(startTime, endTime);
		if(eventList != null){
			for(int i = 0; i < eventList.size(); i++){
				Log.d("dmquan","event title:"+ eventList.get(i).title);
			}
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View cellView = convertView;

		// For reuse
		if (convertView == null) {
			cellView = inflater.inflate(R.layout.custom_cell, null);
		}

		int topPadding = cellView.getPaddingTop();
		int leftPadding = cellView.getPaddingLeft();
		int bottomPadding = cellView.getPaddingBottom();
		int rightPadding = cellView.getPaddingRight();

		TextView dayOfMonthTextView = (TextView) cellView.findViewById(R.id.dayOfMonthTextView);
		TextView firstEventTextView = (TextView) cellView.findViewById(R.id.firstEventTextView);
		TextView secondEventTextView = (TextView) cellView.findViewById(R.id.secondEventTextView);
		TextView thirdEventTextView = (TextView) cellView.findViewById(R.id.thirdEventTextView);
		TextView fourthfirstEventTextView = (TextView) cellView.findViewById(R.id.secondEventTextView);

		dayOfMonthTextView.setTextColor(Color.BLACK);

		// Get dateTime of this cell
		DateTime dateTime = this.datetimeList.get(position);
		Resources resources = context.getResources();

		// Set color of the dates in previous / next month
		if (dateTime.getMonth() != month) {
			dayOfMonthTextView.setTextColor(resources
					.getColor(com.caldroid.R.color.caldroid_darker_gray));
		}

		boolean shouldResetDiabledView = false;
		boolean shouldResetSelectedView = false;

		// Customize for disabled dates and date outside min/max dates
		if ((minDateTime != null && dateTime.lt(minDateTime))
				|| (maxDateTime != null && dateTime.gt(maxDateTime))
				|| (disableDates != null && disableDates.indexOf(dateTime) != -1)) {

			dayOfMonthTextView.setTextColor(CaldroidFragment.disabledTextColor);
			if (CaldroidFragment.disabledBackgroundDrawable == -1) {
				cellView.setBackgroundResource(com.caldroid.R.drawable.disable_cell);
			} else {
				cellView.setBackgroundResource(CaldroidFragment.disabledBackgroundDrawable);
			}

			if (dateTime.equals(getToday())) {
				cellView.setBackgroundResource(com.caldroid.R.drawable.red_border_gray_bg);
			}

		} else {
			shouldResetDiabledView = true;
		}

		// Customize for selected dates
		if (selectedDates != null && selectedDates.indexOf(dateTime) != -1) {
			if (CaldroidFragment.selectedBackgroundDrawable != -1) {
				cellView.setBackgroundResource(CaldroidFragment.selectedBackgroundDrawable);
			} else {
				cellView.setBackgroundColor(resources
						.getColor(com.caldroid.R.color.caldroid_sky_blue));
			}

			dayOfMonthTextView.setTextColor(CaldroidFragment.selectedTextColor);

		} else {
			shouldResetSelectedView = true;
		}

		if (shouldResetDiabledView && shouldResetSelectedView) {
			// Customize for today
			if (dateTime.equals(getToday())) {
				cellView.setBackgroundResource(com.caldroid.R.drawable.red_border);
			} else {
				cellView.setBackgroundResource(com.caldroid.R.drawable.cell_bg);
			}
		}

		dayOfMonthTextView.setText("" + dateTime.getDay());
		ArrayList<EventData> eventOfDay = getEventByDate(dateTime);
		if(eventOfDay != null){
			switch (eventOfDay.size()) {
			case 1:
				firstEventTextView.setVisibility(View.VISIBLE);
				firstEventTextView.setText(eventOfDay.get(0).title);
				break;
			case 2:
				firstEventTextView.setVisibility(View.VISIBLE);
				firstEventTextView.setText(eventOfDay.get(0).title);
				secondEventTextView.setVisibility(View.VISIBLE);
				secondEventTextView.setText(eventOfDay.get(1).title);
				break;
			case 3:
				firstEventTextView.setVisibility(View.VISIBLE);
				firstEventTextView.setText(eventOfDay.get(0).title);
				secondEventTextView.setVisibility(View.VISIBLE);
				secondEventTextView.setText(eventOfDay.get(1).title);
				thirdEventTextView.setVisibility(View.VISIBLE);
				thirdEventTextView.setText(eventOfDay.get(2).title);
				break;

			default:
				firstEventTextView.setVisibility(View.VISIBLE);
				firstEventTextView.setText(eventOfDay.get(0).title);
				secondEventTextView.setVisibility(View.VISIBLE);
				secondEventTextView.setText(eventOfDay.get(1).title);
				thirdEventTextView.setVisibility(View.VISIBLE);
				thirdEventTextView.setText(eventOfDay.get(2).title);
				fourthfirstEventTextView.setVisibility(View.VISIBLE);
				fourthfirstEventTextView.setText(eventOfDay.get(3).title);
				break;
			}
		}

		// Somehow after setBackgroundResource, the padding collapse.
		// This is to recover the padding
		cellView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, context.getResources().getDimensionPixelOffset(R.dimen.calendar_cell_height)));
		cellView.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);
		
		// Set custom color if required
		setCustomResources(dateTime, cellView, dayOfMonthTextView);

		return cellView;
	}
	
	@Override
	public void setAdapterDateTime(DateTime dateTime) {
		super.setAdapterDateTime(dateTime);
		long startTime = datetimeList.get(0).getMilliseconds(TimeZone.getDefault());
		long endTime = datetimeList.get(datetimeList.size() - 1).getMilliseconds(TimeZone.getDefault());
		this.eventList = EventManager.getInstance().getEvents(startTime, endTime);
		if(eventList != null){
			for(int i = 0; i < eventList.size(); i++){
				Log.d("dmquan","event title:"+ eventList.get(i).title);
			}
		}
		
	}
	
	private ArrayList<EventData> getEventByDate(DateTime dateTime){
		ArrayList<EventData> eventOfDay = new ArrayList<EventData>();
		if(eventList != null){
			for(int i = 0; i < eventList.size(); i++){
				if(isEventInDate(eventList.get(i), dateTime)){
					eventOfDay.add(eventList.get(i));
				}
			}
		}
		if(eventOfDay.size() > 0){
			return eventOfDay;
		}
		return null;
	}

	private boolean isEventInDate(EventData eventData, DateTime dateTime) {
		long startTime = dateTime.getMilliseconds(TimeZone.getDefault());
		long endTime = startTime + 24*60*60*1000;
		
		if( (eventData.start >= startTime && eventData.start <= endTime) 
				|| (eventData.end >= startTime && eventData.end <= endTime)  
				|| (eventData.start < startTime && eventData.end > endTime)){
			return true;
		}
		return false;
	}

}
