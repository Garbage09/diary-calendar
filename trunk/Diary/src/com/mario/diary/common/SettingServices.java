package com.mario.diary.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SettingServices {
	protected static SharedPreferences settingPreferences;
	protected static SettingServices instance = new SettingServices();

	public static SettingServices getInstance() {
		return instance;
	}

	public static void put(String key, String value) {
		Editor editor = settingPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public static String getString(String key, String defaultValue) {
		return settingPreferences.getString(key, defaultValue);
	}

	public static void putBoolean(String key, boolean value) {
		Editor editor = settingPreferences.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	public static Boolean getBoolean(String key, boolean defaultValue) {
		return settingPreferences.getBoolean(key, defaultValue);
	}

	public static void putInteger(String key, int value) {
		Editor editor = settingPreferences.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	public static Integer getInteger(String key, int defaultValue) {
		return settingPreferences.getInt(key, defaultValue);
	}

	public void init(Context context) {
		settingPreferences = context.getSharedPreferences(
				SettingConstants.PREFERENCE_NAME, Context.MODE_PRIVATE);
	}
}