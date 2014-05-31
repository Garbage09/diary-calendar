package com.mario.diary;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mario.diary.adapter.DiaryListAdapter;
import com.mario.diary.adapter.DiaryListAdapterWrapper;
import com.mario.diary.common.SettingConstants;
import com.mario.diary.model.Diary;

public class FragmentDiary extends android.support.v4.app.Fragment {
	private ArrayList<Diary> diaryArrayList;
	private ListView mDiaryListView;
	private View view;
	private Context context;

	public FragmentDiary(Context context) {
		this.context = context;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.activity_diary, container, false);
		mDiaryListView = (ListView) view.findViewById(R.id.diaryListView);
		diaryArrayList = popularDiary();
		DiaryListAdapter diaryListAdapter = new DiaryListAdapter(context,
				R.layout.item_diary_layout, diaryArrayList);

		// DiaryListAdapter diaryListAdapter = new DiaryListAdapter(
		// DiaryActivity.this, R.layout.item_diary_layout);

		DiaryListAdapterWrapper dlaw = new DiaryListAdapterWrapper(
				diaryListAdapter);

		mDiaryListView.setAdapter(dlaw);
		mDiaryListView.setSelection(Integer.MAX_VALUE / 2);

		// setSelection(SettingConstants.NUMBER_OF_DIARY_RECORDS / 2);

		// setListAdapter(dlaw);
		Log.d("DIARY", "1");
		// setSelection(Integer.MAX_VALUE/2);
		// setSelection(20 / 2);
		Log.d("DIARY", "2");
		return view;
	}

	public ArrayList<Diary> popularDiary() {
		ArrayList<Diary> rs = new ArrayList<Diary>();

		// Date date = new Date();
		Calendar c = Calendar.getInstance();
		// c.setTime(date);

		int dom = c.get(Calendar.DAY_OF_MONTH);
		int dow = c.get(Calendar.DAY_OF_WEEK);
		for (int i = 0; i < SettingConstants.NUMBER_OF_DIARY_RECORDS; i++) {
			Log.i("DIARY", "Day of month: " + dom);
			Log.i("DIARY", "Day of week: " + dow);
			Diary d = new Diary(dom, dow);
			rs.add(d);
			dom = dom + 1;
			dow = dow + 1;
		}

		return rs;
	}
}
