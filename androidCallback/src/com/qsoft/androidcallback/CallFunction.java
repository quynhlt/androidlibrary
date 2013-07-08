package com.qsoft.androidcallback;

import java.lang.reflect.InvocationTargetException;

import android.os.Handler;
import android.util.Log;

/**
 * @author quynhlt
 * 
 */
public class CallFunction {
	private String tag = "[CallFunction]";

	/**
	 * Call a function affer delay contains parameters.
	 * 
	 * @param delay
	 *            The delay (in milliseconds) until the Runnable will be
	 *            executed.
	 * @param methodName
	 *            The name of the method to call.
	 * @param className
	 *            Class contains method to call.
	 * @param params
	 *            The parameter of the method to call.
	 */
	public CallFunction(final int delay, final String methodName, final Object className, final Object[] params) {
		final Handler handler = new Handler();
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				CallBack callBack;
				try {
					callBack = new CallBack(className, methodName);
					if (params == null) {
						callBack.invoke();
					} else {
						callBack.invoke(params);
					}
					Log.d(tag, "Run " + methodName + "() function of " + className + " with time delay is " + delay);
				} catch (InvocationTargetException e) {
					Log.d(tag, e.getMessage());
				} catch (IllegalAccessException e) {
					Log.d(tag, e.getMessage());
				} catch (NoSuchMethodException e) {
					Log.d(tag, e.getMessage());
				} catch (Exception e) {
					Log.d(tag, e.getMessage());
				}
			}
		};
		handler.postDelayed(runnable, delay);
	}

}
