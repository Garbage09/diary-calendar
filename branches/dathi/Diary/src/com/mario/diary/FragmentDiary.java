package com.mario.diary;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mario.diary.adapter.DiaryListAdapter;
import com.mario.diary.adapter.DiaryListAdapterWrapper;

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
		mDiaryListView.setSelection(Integer.MAX_VALUE / 2);
		return view;
	}
}
