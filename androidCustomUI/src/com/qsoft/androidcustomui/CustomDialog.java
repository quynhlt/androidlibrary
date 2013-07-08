/**
 * 
 */
package com.qsoft.androidcustomui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.qsoft.androidcallback.CallFunction;

/**
 * @author quynhlt
 * 
 *         HOW TO USE
 * 
 *         This Library use library libs/androidcallback.jar.
 * 
 *         To use this library please copy bin/androidcustomui.jar
 * 
 *         and androidcallback.jar to libs your project.
 * 
 * */
public class CustomDialog {
	private PopupWindow popupWindow = null;
	private static CustomDialog customDialog = null;
	private ProgressDialog progressDialog = null;

	public static CustomDialog getInstance() {
		if (customDialog == null) {
			customDialog = new CustomDialog();
		}
		return customDialog;
	}

	/**
	 * Dialog showing a progress indicator and an optional text message or view.
	 * Only a text message or a view can be used at the same time.
	 * 
	 * @param context
	 *            Context call loading to show
	 * @param message
	 *            Message to display when display ProgressDialog
	 * @param title
	 *            Title to display when display ProgressDialog
	 * 
	 */
	public void showLoading(Context context, String title, String message) {
		dismissLoading();
		try {
			progressDialog = ProgressDialog.show(context, title, message, true, true);
			progressDialog.setCancelable(false);
		} catch (Exception e) {
		}
	}

	/**
	 * Dismiss this dialog, removing it from the screen. This method can be
	 * invoked safely from any thread. Note that you should not override this
	 * method to do cleanup when the dialog is dismissed, instead implement that
	 * in onStop().
	 */
	public void dismissLoading() {
		try {
			if (progressDialog != null && progressDialog.isShowing()) {
				progressDialog.dismiss();
				progressDialog = null;
			}
		} catch (Exception e) {
		}
	}

	/**
	 * Whether the dialog is currently showing.
	 * 
	 * @return boolean
	 */
	public boolean isShowing() {
		if (progressDialog != null) {
			return progressDialog.isShowing();
		}
		return false;
	}

	/**
	 * 
	 * Create a new empty, non focusable popup window of dimension (0,0). The
	 * popup does provide a background with specific view.
	 * 
	 * @param context
	 *            Context call PopupWindow
	 * @param view
	 *            View of PopupWindow to display
	 */
	public void showPopup(Context context, View view) {
		if (!isShowingPopup()) {
			popupWindow = new PopupWindow(context);
			popupWindow = new PopupWindow(view, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
			popupWindow.setOutsideTouchable(true);
			popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
		}

	}

	/**
	 * Indicate whether this popup window is showing on screen.
	 * 
	 * @return true if the popup is showing, false otherwise
	 */
	public boolean isShowingPopup() {
		if (popupWindow != null) {
			return popupWindow.isShowing();
		}
		return false;
	}

	/**
	 * Dispose of the popup window.
	 */
	public void dismissPopup() {
		popupWindow.dismiss();
	}

	/**
	 * A AlertDialog showing a progress indicator and an optional text message
	 * or view
	 * 
	 * @param activity
	 *            Activity to create builder of alert dialog. Contain method to
	 *            call when select positive button
	 * @param title
	 *            Title of AlertDialog
	 * @param message
	 *            Message of AlertDialog
	 * @param buttonOk
	 *            Text of positive button
	 * @param buttonCacel
	 *            Text of cancel button
	 * @param isConfirm
	 *            Boolean value allow show positive button or not
	 * @param methodName
	 *            Method to call when select positive button
	 * @param params
	 *            Params of method to call when select positive button
	 */
	public static void showAlertDialog(final Activity activity, String title, String message, String buttonOk, String buttonCacel, boolean isConfirm,
			final String methodName, final Object[] params) {
		showAlertDialog(activity, activity, title, message, buttonOk, buttonCacel, isConfirm, methodName, params);
	}

	/**
	 * A AlertDialog showing a progress indicator and an optional text message
	 * or view
	 * 
	 * @param object
	 *            Object class contain method to call when select positive
	 *            button
	 * @param activity
	 *            Activity to create builder of alert dialog.
	 * 
	 * @param title
	 *            Title of AlertDialog
	 * @param message
	 *            Set the message to display.
	 * @param buttonOk
	 *            The text to display in the negative button
	 * @param buttonCacel
	 *            The text to display in the negative button
	 * @param isConfirm
	 *            Boolean value allow show positive button or not
	 * @param methodName
	 *            Method to call when select positive button
	 * @param params
	 *            params of method to call when select positive button
	 */
	public static void showAlertDialog(final Object object, final Activity activity, String title, String message, String buttonOk,
			String buttonCacel, boolean isConfirm, final String methodName, final Object[] params) {
		Builder alertDialog = new AlertDialog.Builder(activity);
		alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
		alertDialog.setTitle(title);
		alertDialog.setMessage(message);
		alertDialog.setCancelable(false);
		if (methodName == null) {
			alertDialog.setNegativeButton(buttonOk, null);
		} else {
			alertDialog.setPositiveButton(buttonOk, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					new CallFunction(0, methodName, activity, params);
				}
			});
		}
		if (isConfirm) {
			alertDialog.setNegativeButton(buttonCacel, null);
		}
		alertDialog.show();
	}

	/**
	 * A Toast showing a text message
	 * 
	 * @param context
	 *            Context create toast
	 * @param message
	 *            Text message to show
	 */
	public static void toastMaketext(Context context, String message) {
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, message, duration);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}
}
