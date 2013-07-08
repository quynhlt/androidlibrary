/**
 * 
 */
package com.qsoft.testlibrary;

import android.app.Activity;
import android.util.Log;

/**
 * @author quynhlt
 *
 */
public class NewActivity extends Activity {
	public void logMessage(String message){
		Log.e("logMessage", message);
	}
	
	public void logObject(MyEntity entity){
		Log.e("New message of entity", entity.getMessage());
	}
}
