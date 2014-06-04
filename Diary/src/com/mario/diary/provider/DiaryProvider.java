package com.mario.diary.provider;

import com.mario.diary.database.DatabaseHandler;
import com.mario.diary.model.Diary;

import android.content.ContentValues;
import android.content.Context;
import android.provider.BaseColumns;

public class DiaryProvider {
	public final static String TABLE_NAME = "diary";

	public final class Columns implements BaseColumns {
		public final static String TEXT_CONTENT = "_text_content";
		public final static String CREATED_DATE = "_created_date";
		public final static String EVENT_ID = "_event_id";
	}

	public static int addDiary(Context context, Diary diary) {
		ContentValues cv = new ContentValues();
		cv.put(Columns.TEXT_CONTENT, diary.getContentDiary());
		cv.put(Columns.CREATED_DATE, diary.getCreatedDate());
		cv.put(Columns.EVENT_ID, diary.getEventId());

		int result = 0;
		if (!isExist(context, diary)) {
			DatabaseHandler db = DatabaseHandler.getDatabase(context);
			if (db.insert(TABLE_NAME, null, cv) == -1) {
				result = -1;
			} else {
				result = 1;
			}
		}
		return result;
	}

	public static int deleteDiary(Context context, Diary diary) {
		String selection = String.format("%s = \"%s\"", Columns.CREATED_DATE,
				diary.getCreatedDate());

		int result = 0;
		DatabaseHandler db = DatabaseHandler.getDatabase(context);

		if (db.delete(TABLE_NAME, selection, null) > 0) {
			result = 1;
		}
		return result;
	}

	private static boolean isExist(Context context, Diary diary) {
		// TODO Auto-generated method stub
		return false;
	}
}
