package com.mario.diary.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mario.diary.adapter.CustomGridCalendarAdapter;
import com.mario.diary.common.EventManager;
import com.mario.diary.model.CalendarData;
import com.mario.diary.model.EventData;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidGridAdapter;

public class CustomCaldroidFragment extends CaldroidFragment {
	private ArrayList<CalendarData> calendars;
	private ArrayList<EventData> events;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
//    	calendars = EventManager.getInstance().getCalendarList();
//    	Date today = new Date();
//    	Calendar c = Calendar.getInstance();
//        c.add(Calendar.DATE, 1);
//        
//        
//        Date endate = new Date(c.getTimeInMillis());
////        EventManager.getInstance().getEventAll();
//    	events = EventManager.getInstance().getEvents(today.getTime(), endate.getTime());
    	
//    	EventManager.getInstance().listEventTableColumn();
    	return super.onCreateView(inflater, container, savedInstanceState);
    }
	@Override
	public CaldroidGridAdapter getNewDatesGridAdapter(int month, int year) {
		// TODO Auto-generated method stub
		return new CustomGridCalendarAdapter(getActivity(), month, year,
				getCaldroidData(), extraData);
	}

}
