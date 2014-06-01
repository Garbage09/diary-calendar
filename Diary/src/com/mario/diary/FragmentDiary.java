package com.mario.diary;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
import com.mario.diary.common.Utils;
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

		DiaryListAdapterWrapper dlaw = new DiaryListAdapterWrapper(
				diaryListAdapter);

		mDiaryListView.setAdapter(dlaw);
		mDiaryListView.setSelection(Integer.MAX_VALUE / 2);
		return view;
	}

	public ArrayList<Diary> popularDiary() {
		ArrayList<Diary> rs = new ArrayList<Diary>();
		Calendar c = Calendar.getInstance();

		int doy = c.get(Calendar.DAY_OF_YEAR);

		int dom, month, numDays = -1;
		String strDow = "";
		String strDiary = "";

		for (int i = 0; i < SettingConstants.NUMBER_OF_DIARY_RECORDS; i++) {
			// Log.i("DIARY", "Day of month: " + dom);			
			month = Utils.getMonthFromDayOfYear(c, doy);
			numDays = Utils.getLastDayInMonth(c, doy);
			dom = Utils.getDayOfMonthFromDayOfYear(c, doy);
			strDow = Utils.getDayOfWeekFromDayOfYear(c, doy);
			strDiary = c.getTime().toString();
			
			Diary d = new Diary(dom, strDow, strDiary);
			rs.add(d);

			doy = doy + 1;
			c.set(Calendar.DAY_OF_YEAR, doy);
		}

		return rs;
	}
}
