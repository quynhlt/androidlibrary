/**
 * 
 */
package com.qsoft.androidutility;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * @author quynhlt
 *
 */
public class FragmentHelper {

		/**
		 * @param fragmentMng
		 *            Interface for interacting with Fragment objects inside of an
		 *            Activity.
		 * @param tagName
		 *            The name of tag fragment to add.
		 * @param viewId
		 *            The id of view to a.dd fragment
		 * @param fragment
		 *            The new instance of fragment to add.
		 */
		public static void addFragment(FragmentManager fragmentMng, int viewId, Fragment fragment, String tagName) {
			FragmentTransaction fragmentTrans = fragmentMng.beginTransaction();
			fragmentTrans.add(viewId, fragment, tagName);
			fragmentTrans.commit();
		}

		/**
		 * @param fragmentMng
		 *            Interface for interacting with Fragment objects inside of an
		 *            Activity.
		 * @param tagName
		 *            The name of tag fragment to remove.
		 */
		public static void removeFragment(FragmentManager fragmentMng, String tagName) {
			FragmentTransaction fragmentTrans = fragmentMng.beginTransaction();
			if (fragmentMng.findFragmentByTag(tagName) != null) {
				fragmentTrans.remove(fragmentMng.findFragmentByTag(tagName));
				fragmentTrans.commit();
			}
		}

		/**
		 * @param fragmentMng
		 *            Interface for interacting with Fragment objects inside of an
		 *            Activity.
		 * @param tagName
		 *            The name of tag fragment to find.
		 * @return a fragment.
		 */
		public static Fragment getFragment(FragmentManager fragmentMng, String tagName) {
			return fragmentMng.findFragmentByTag(tagName);
		}
	}

