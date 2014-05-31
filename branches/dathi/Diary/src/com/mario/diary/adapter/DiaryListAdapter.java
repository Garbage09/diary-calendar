package com.mario.diary.adapter;

import java.util.ArrayList;

import com.mario.diary.common.SettingConstants;
import com.mario.diary.model.Diary;
import com.mario.diary.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
		if (view == null) {
			LayoutInflater li = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = li.inflate(mResource, null);
			final Diary diary = (Diary) mItems.get(currentRealPostion);

			TextView dayTextView = (TextView) view
					.findViewById(R.id.dayTextView);
			dayTextView.setText(String.valueOf(diary.getDay()));

			TextView dowTextView = (TextView) view
					.findViewById(R.id.dayOfWeekTextView);
			dowTextView.setText(String.valueOf(diary.getDayOfWeek()));
		}
		return view;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return SettingConstants.NUMBER_OF_DIARY_RECORDS;
	}

	@Override
	public Diary getItem(int position) {
		// TODO Auto-generated method stub
		return null;
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
}
