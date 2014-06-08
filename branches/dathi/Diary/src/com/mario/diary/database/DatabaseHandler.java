package com.mario.diary.database;

import com.mario.diary.provider.DiaryProvider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler {

	private final static int DATABASE_VERSION = 1;
	private final static String DATABASE_NAME = "mDiaryDB";
	
	private static DatabaseHandler mInstance = new DatabaseHandler();

	private static SQLiteDatabase mWritable;
	private static SQLiteDatabase mReadable;

	public synchronized static DatabaseHandler getDatabase(Context context) {
		if ((mWritable == null) || (mReadable == null)) {
			DatabaseHelper helper = new DatabaseHelper(context, DATABASE_NAME);
			if (mWritable == null) {
				mWritable = helper.getWritableDatabase();
			}
			if (mReadable == null) {
				mReadable = helper.getReadableDatabase();
			}
		}
		return mInstance;
	}

	@Override
	protected void finalize() {
		if ((mWritable != null) && mWritable.isOpen()) {
			mWritable.close();
		}
		if ((mReadable != null) && mReadable.isOpen()) {
			mReadable.close();
		}
	}
		
	private static final class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context context, String name) {
			super(context, name, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			updateDatabase(db);
		}	

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + DiaryProvider.TABLE_NAME);
			updateDatabase(db);
		}
		
		private void updateDatabase(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE IF NOT EXISTS " + DiaryProvider.TABLE_NAME + " ("
					+ DiaryProvider.Columns._ID				+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ DiaryProvider.Columns.TEXT_CONTENT	+ " TEXT,"
					+ DiaryProvider.Columns.CREATED_DATE	+ " LONG,"
					+ DiaryProvider.Columns.EVENT_ID		+ " TEXT"
					+ ");");
		}
		
	}

	public SQLiteDatabase getReadableDatabase() {
		return mReadable;
	}

	public SQLiteDatabase getWritableDatabase() {
		return mWritable;
	}
	
	public long insert(String table, String nullColumnHack, ContentValues values) {
		long rowId = mWritable.insert(table, nullColumnHack, values);
		return rowId;
	}
	
	public int delete(String table, String whereClause, String[] whereArgs) {
		int count = mWritable.delete(table, whereClause, whereArgs);
		return count;
	}

	public int update(String table, ContentValues values, String whereClause,
			String[] whereArgs) {
		int count = mWritable.update(table, values, whereClause, whereArgs);
		return count;
	}
	
	public Cursor query(String table, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		Cursor c = mReadable.query(table, projection, selection, selectionArgs, null,
				null, sortOrder);
		return c;
	}
}