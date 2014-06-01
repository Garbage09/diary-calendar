package com.mario.diary.adapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mario.diary.R;
import com.mario.diary.common.SettingConstants;
import com.mario.diary.common.Utils;
import com.mario.diary.model.Diary;

public class DiaryListAdapter extends ArrayAdapter<Diary> {

	private Context mContext;
	private int mResource;
	private ArrayList<Diary> mItems;
	private int currentRealPostion;

	public DiaryListAdapter(Context context, int resource,
			ArrayList<Diary> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		mContext = context;
		mResource = resource;
		mItems = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = convertView;
		Log.i("DiaryListAdapter", "position: " + position);
		Log.i("DiaryListAdapter", "currentRealPostion: " + currentRealPostion);

		if (view == null) {
			LayoutInflater li = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = li.inflate(mResource, null);
		}

		TextView dayTextView = (TextView) view.findViewById(R.id.dayTextView);
		TextView dowTextView = (TextView) view
				.findViewById(R.id.dayOfWeekTextView);
		TextView diaryTextView = (TextView) view
				.findViewById(R.id.diaryTextView);

		// final Diary diary = (Diary) mItems.get(position);
		//
		// Log.i("DiaryListAdapter", "Day of month: " + diary.getDay());
		// Log.i("DiaryListAdapter", "Day of week: " + diary.getDayOfWeek());

		// dayTextView.setText(String.valueOf(diary.getDay()));
		// dowTextView.setText(String.valueOf(diary.getDayOfWeek()));
		// diaryTextView.setText(String.valueOf(diary.getStrContentDiary()));

		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, position);
		int year = c.get(Calendar.YEAR);

		int doy = c.get(Calendar.DAY_OF_YEAR);
		int month = Utils.getMonthFromDayOfYear(c, doy) + 1;
		int numDaysInMonth = Utils.getLastDayInMonth(c, doy);

		int dom = Utils.getDayOfMonthFromDayOfYear(c, doy);
		String strDow = Utils.getDayOfWeekFromDayOfYear(c, doy);
		String strDiary = c.getTime().toString() + " - " + String.valueOf(year);
//		if (dom > numDaysInMonth) {
//			dom = 1;
//		} else if (dom > position && position > mItems.size()) {
//			dom++;
//		}

		dayTextView.setText(String.valueOf(dom));
		dowTextView.setText(strDow);
		diaryTextView.setText(strDiary);

		return view;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return SettingConstants.NUMBER_OF_DIARY_RECORDS;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setCurrentRealPosition(int position) {
		// TODO Auto-generated method stub
		currentRealPostion = position;
	}

	public int getCurrentRealPosition() {
		return currentRealPostion;
	}
}
