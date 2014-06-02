package com.mario.diary.adapter;

import java.util.Calendar;
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
	private int currentRealPostion;	

	public DiaryListAdapter(Context context, int resource) {
		super(context, resource);
		// TODO Auto-generated constructor stub
		mContext = context;
		mResource = resource;
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
			Log.i("DiaryListAdapter", "VIEW = NULL, position: " + position);
		}

		TextView dayTextView = (TextView) view.findViewById(R.id.dayTextView);
		TextView dowTextView = (TextView) view
				.findViewById(R.id.dayOfWeekTextView);
		TextView diaryTextView = (TextView) view
				.findViewById(R.id.diaryTextView);

		Calendar c = Calendar.getInstance();		
		c.add(Calendar.DATE, currentRealPostion - SettingConstants.HALF_MAX_VALUE);
		
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int dom = c.get(Calendar.DAY_OF_MONTH);
		int numDaysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		int dow = c.get(Calendar.DAY_OF_WEEK);
		String strDow = Utils.getNameDayOfWeek(dow);
		String strDiary = c.getTime().toString() + " - " + month + " - " + numDaysInMonth;

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
