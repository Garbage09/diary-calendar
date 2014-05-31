package com.mario.diary;

import java.util.ArrayList;
import java.util.Calendar;

import com.mario.diary.adapter.DiaryListAdapter;
import com.mario.diary.adapter.DiaryListAdapterWrapper;
import com.mario.diary.common.SettingConstants;
import com.mario.diary.model.Diary;
import com.mario.diary.R;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class DiaryActivity extends ListActivity {

	private ArrayList<Diary> diaryArrayList;
	private ListView mDiaryListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diary);

		//mDiaryListView = (ListView) findViewById(R.id.diaryListView);
		diaryArrayList = popularDiary();
		DiaryListAdapter diaryListAdapter = new DiaryListAdapter(DiaryActivity.this,
				R.layout.item_diary_layout, diaryArrayList);
		
		DiaryListAdapterWrapper dlaw = new DiaryListAdapterWrapper(diaryListAdapter);
		
		//mDiaryListView.setAdapter(dlaw);		
		//setSelection(SettingConstants.NUMBER_OF_DIARY_RECORDS / 2);
		
		setListAdapter(dlaw);
        setSelection(Integer.MAX_VALUE/2);
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
