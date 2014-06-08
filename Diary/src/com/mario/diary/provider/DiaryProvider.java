package com.mario.diary.provider;

import com.mario.diary.database.DatabaseHandler;
import com.mario.diary.model.Diary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
		cv.put(Columns._ID, diary.getDiaryId());
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
		String selection = String.format(
				"%s = %s AND %s = %s AND %s = \"%s\" AND %s = \"%s\"",
				Columns._ID, String.valueOf(diary.getDiaryId()),
				Columns.CREATED_DATE, String.valueOf(diary.getCreatedDate()),
				Columns.TEXT_CONTENT, diary.getContentDiary(),
				Columns.EVENT_ID, diary.getEventId());

		DatabaseHandler db = DatabaseHandler.getDatabase(context);

		Cursor c = db.query(TABLE_NAME, null, selection, null, null);

		if (c == null) {
			return false;
		}

		if (c.getCount() == 0) {
			c.close();
			return false;
		}

		c.close();
		return true;
	}

	public static Diary getDiaryById(Context context, long diaryId) {
		String selection = String.format("%s = %s", Columns._ID,
				String.valueOf(diaryId));

		DatabaseHandler db = DatabaseHandler.getDatabase(context);

		Cursor c = db.query(TABLE_NAME, null, selection, null, null);

		if (c.getCount() > 0) {
			c.moveToFirst();
			Diary diary = new Diary();
			diary.setContentDiary(c.getString(0));
			diary.setCreatedDate(c.getLong(1));
			diary.setEventId(c.getString(2));
			return diary;
		}
		return null;
	}

}
