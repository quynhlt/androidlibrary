/**
 * 
 */
package com.qsoft.androidutility;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * @author quynhlt
 * 
 */
public class IntentHelper {

	/**
	 * @param parrent
	 *            Activity parrent call another activity
	 * @param navigate
	 *            Activity is call by parrent activity
	 * @param data
	 *            data send when call activity
	 * @param requestcode
	 *            request code when send activity result
	 */
	public static void callActivity(Activity parrent, Class<?> navigate, Serializable data, List<?> serializables, int requestcode, String name) {
		Intent intent = new Intent(parrent, navigate);
		if (data != null) {
			intent.putExtra(name, data);
		}
		if (serializables != null) {
			Bundle bundle = new Bundle();
			bundle.putSerializable(name, (ArrayList<?>) serializables);
			intent.putExtras(bundle);
		}
		if (requestcode == -1) {
			parrent.startActivity(intent);
		} else {
			parrent.startActivityForResult(intent, requestcode);
		}
	}

	/**
	 * @param activity
	 *            Activity to finish
	 * @param resultcode
	 *            result code to finish
	 */
	public static void finishActivityReload(Activity activity, int resultcode) {
		Intent intent = activity.getIntent();
		activity.setResult(resultcode, intent);
		activity.finish();
		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(0);
	}

	/**
	 * @param activity
	 *            Activity to finish
	 * @param resultcode
	 *            result code to finish
	 */
	public static void finishActivity(Activity activity, Serializable data, List<?> serializables, int resultcode, String name) {
		Intent intent = activity.getIntent();
		if (data != null) {
			intent.putExtra(name, data);
		}
		if (serializables != null) {
			Bundle bundle = new Bundle();
			bundle.putSerializable(name, (ArrayList<?>) serializables);
			intent.putExtras(bundle);
		}
		activity.setResult(resultcode, intent);
		activity.finish();
	}

	/**
	 * @param activity
	 *            Current activity to get data
	 * @return a object Serializable
	 */
	public static Serializable getDataIntent(Intent intent, String name) {
		Serializable serializable = null;
		Bundle extras = intent.getExtras();
		if (extras != null) {
			serializable = (Serializable) extras.get(name);
		}
		return serializable;
	}

	/**
	 * @param activity
	 *            Current activity to get data
	 * @return a list objects.
	 */
	public static List<?> getListDataIntent(Intent intent, String name) {
		List<?> serializables = null;
		Bundle extras = intent.getExtras();
		if (extras != null) {
			serializables = (ArrayList<?>) extras.getSerializable(name);
		}
		return serializables;
	}

}
