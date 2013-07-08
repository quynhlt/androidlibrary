package com.qsoft.testlibrary;

import com.qsoft.androidcallback.CallFunction;
import com.qsoft.androidcustomui.CustomDialog;
import com.qsoft.androidfilecache.SharedPreferencesManager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {
	private static final String KEY = "test";
	private SharedPreferencesManager preferencesManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		preferencesManager = new SharedPreferencesManager(this);

		findViewById(R.id.btShowtoast).setOnClickListener(clickListener);
		findViewById(R.id.btShowAlert).setOnClickListener(clickListener);
		findViewById(R.id.btGetvalue).setOnClickListener(clickListener);
		findViewById(R.id.btCallBack).setOnClickListener(clickListener);

		preferencesManager.setValue(KEY, "test save preferences");
	}

	private void showAlertConfirm() {
		Object[] params = { "send NEW VALUE" };
		String title = "Message";
		String message = "Are you sure you want to send message?";
		String buttonOk = "Call";
		String buttonCacel = "Cancel";
		boolean isConfirm = true;
		String methodName = "showAlert";

		CustomDialog.showAlertDialog(this, title, message, buttonOk, buttonCacel, isConfirm, methodName, params);
	}

	public void showAlert(String message) {
		String title = "Message";
		String buttonOk = "OK";
		CustomDialog.showAlertDialog(this, title, message, buttonOk, null, false, null, null);
	}

	private void showToast() {
		CustomDialog.toastMaketext(this, "Click button showToast");
		Log.e("Run methob", "showToast");
	}

	private void getValue() {
		String message = preferencesManager.getString(KEY);
		CustomDialog.showAlertDialog(this, "Value saved", message, "Done", null, false, null, null);
	}

	private void callback() {
		// logMessage declare public method
		new CallFunction(0, "logMessage", new NewClass(), null);
		
		Object[] pamrams = {"send message from Main Activity"};
		new CallFunction(2000, "logMessage", new NewActivity(), pamrams);
		
		
		MyEntity myEntity = new MyEntity();
		myEntity.setMessage("Message of Entity from main activity");
		Object[] pamramsObj = {myEntity};
		new CallFunction(4000, "logObject", new NewActivity(), pamramsObj);
	}

	private final OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.btShowtoast:
				showToast();
				break;
			case R.id.btShowAlert:
				showAlertConfirm();
				break;
			case R.id.btGetvalue:
				getValue();
				break;
			case R.id.btCallBack:
				callback();
				break;
			default:
				break;
			}
		}
	};
}
