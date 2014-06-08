package com.mario.diary.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.mario.diary.R;
import com.mario.diary.R.id;
import com.mario.diary.R.layout;
import com.mario.diary.adapter.DiaryListAdapter;
import com.mario.diary.adapter.DiaryListAdapterWrapper;
import com.mario.diary.common.SettingConstants;

public class FragmentDiary extends android.support.v4.app.Fragment {
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

		DiaryListAdapter diaryListAdapter = new DiaryListAdapter(context,
				R.layout.item_diary_layout);

		DiaryListAdapterWrapper dlaw = new DiaryListAdapterWrapper(
				diaryListAdapter);

		mDiaryListView.setAdapter(dlaw);
		mDiaryListView.setSelection(SettingConstants.HALF_MAX_VALUE);

		mDiaryListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> paramAdapterView,
					View paramView, int position, long id) {
				// TODO Auto-generated method stub
				Log.i("FragmentDiary", "position: " + position);
				Log.i("FragmentDiary", "id: " + id);
				
			}

		});

		return view;
	}
}
