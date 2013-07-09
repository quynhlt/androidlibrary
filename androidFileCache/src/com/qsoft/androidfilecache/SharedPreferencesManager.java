package com.qsoft.androidfilecache;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @author quynhlt
 * 
 *         HOW TO USE
 * 
 *         SharedPreferencesManager preferencesManager = new
 *         SharedPreferencesManager(context);
 * 
 *         //Allow set string, integer and boolean value. To set new value call
 *         method:
 * 
 *         preferencesManager.setValue(KEY, "test save preferences");
 * 
 *         //To get value call method:
 * 
 *         String value = preferencesManager.getString(KEY);
 * 
 */
public class SharedPreferencesManager {
	public static final String FILE_NAME_SHARE = "Androidcache";
	private SharedPreferences sharedPreferences = null;
	private Editor editor;

	/**
	 * @param context
	 *            Context use to set value in sharedPreferences Create a new
	 *            Editor for these preferences, through which you can make
	 *            modifications to the data in the preferences and atomically
	 *            commit those changes back to the SharedPreferences object.
	 * 
	 *            Note that you must call commit() to have any changes you
	 *            perform in the Editor actually show up in the
	 *            SharedPreferences.
	 */
	public SharedPreferencesManager(Context context) {
		sharedPreferences = context.getApplicationContext().getSharedPreferences(FILE_NAME_SHARE, Activity.MODE_PRIVATE);
		editor = sharedPreferences.edit();
	}

	/**
	 * Mark in the editor to remove all values from the preferences.
	 */
	public void clearCache() {
		sharedPreferences.edit().clear().commit();
	}

	/**
	 * @param key
	 *            The name of the preference to modify.
	 * @param value
	 *            The new string value for the preference.
	 */
	public void setValue(String key, String value) {
		editor.putString(key, value);
		editor.commit();
	}

	/**
	 * @param key
	 *            The name of the preference to modify.
	 * @param value
	 *            The new boolean value for the preference.
	 */
	public void setValue(String key, Boolean value) {
		editor.putBoolean(key, value);
		editor.commit();
	}

	/**
	 * @param key
	 *            The name of the preference to modify.
	 * @param value
	 *            The new integer value for the preference.
	 */
	public void setValue(String key, Integer value) {
		editor.putInt(key, value);
		editor.commit();
	}

	/**
	 * Allow get a string value in sharedPreferences
	 * 
	 * @param key
	 *            The name of the preference to retrieve.
	 * @return String value
	 */
	public String getString(String key) {
		return sharedPreferences.getString(key, "");
	}

	/**
	 * @param key
	 *            The name of the preference to retrieve.
	 * @return boolean value
	 */
	public boolean getBoolean(String key) {
		return sharedPreferences.getBoolean(key, false);
	}

	/**
	 * @param key
	 *            The name of the preference to retrieve.
	 * @return int value
	 */
	public int getInt(String key) {
		return sharedPreferences.getInt(key, 0);
	}
}
