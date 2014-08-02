package com.mario.diary.adapter;

import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.text.format.DateFormat;
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
import com.mario.diary.provider.DiaryProvider;

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
		int numDaysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		int dom = c.get(Calendar.DAY_OF_MONTH);
		
		int dow = c.get(Calendar.DAY_OF_WEEK);
		String strDow = Utils.getNameDayOfWeek(dow);
		
		dayTextView.setText(String.valueOf(dom));
		dowTextView.setText(strDow);					
				
		Diary diary = DiaryProvider.getDiaryById(mContext, currentRealPostion);
				
		if(diary != null) {
			Log.i("DiaryListAdapter", "year: " + year);
			Log.i("DiaryListAdapter", "month: " + month);
			Log.i("DiaryListAdapter", "day of month: " + dom);
			Log.i("DiaryListAdapter", "diaryId = currentRealPostion: " + currentRealPostion);
			String createDate = DateFormat.format("dd/MM/yyyy hh:mm:ss", new Date(diary.getCreatedDate())).toString();
			Log.i("DiaryListAdapter", "createDate: " + createDate);
			Log.i("DiaryListAdapter", "diaryContent: " + diary.getContentDiary());
			
			diaryTextView.setText(diary.getContentDiary());	
		}		
		
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
