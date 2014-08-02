package com.mario.diary.view;

import java.util.Calendar;
import java.util.Date;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.mario.diary.R;
import com.mario.diary.adapter.DiaryListAdapter;
import com.mario.diary.adapter.DiaryListAdapterWrapper;
import com.mario.diary.common.SettingConstants;
import com.mario.diary.model.Diary;
import com.mario.diary.provider.DiaryProvider;

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

		final DiaryListAdapter diaryListAdapter = new DiaryListAdapter(context,
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
				final Dialog dialog = new Dialog(context);
				dialog.setContentView(R.layout.dialog_diary);
				dialog.setTitle("Add Diary");
				
				// diary Id
				final int currentRealPostion = position;
				
				Button dialogButton = (Button) dialog
						.findViewById(R.id.okContentDiaryButton);
				dialogButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// diary content
						final EditText editText = (EditText) dialog
								.findViewById(R.id.contentDiaryEditText);
						String diaryContent = editText.getText().toString();
							
						Calendar c1 = Calendar.getInstance();
						String createDate1 = DateFormat.format("dd/MM/yyyy hh:mm:ss", new Date(c1.getTimeInMillis())).toString();
						
						if (!diaryContent.isEmpty()) {
							// event Id
							String eventId = null;
							
							// date created
							Calendar c = Calendar.getInstance();
							c.add(Calendar.DATE, currentRealPostion - SettingConstants.HALF_MAX_VALUE);
							long dateCreated = c.getTimeInMillis();
							// debug					
							String createDate = DateFormat.format("dd/MM/yyyy hh:mm:ss", new Date(dateCreated)).toString();
							Log.i("FragmentDiary - Construct Diary", "createDate: " + createDate);
														
							// construct diary object
							Diary diary = new Diary(currentRealPostion, diaryContent, eventId, dateCreated);
							
							// add diary to database 
							DiaryProvider.addDiary(context, diary);		
							
							// set diary content to item diaries list  
							TextView diaryTextView = (TextView) view.findViewById(R.id.diaryTextView);
							diaryTextView.setText(diaryContent);
						}
						
						// close "Add" dialog
						dialog.dismiss();
					}
				});

				dialog.show();
			}

		});

		return view;
	}
}
