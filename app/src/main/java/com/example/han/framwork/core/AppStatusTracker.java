package com.example.han.framwork.core;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.example.han.framwork.constant.ConstantValues;

/**
 * Created by han on 2016/7/5.
 */
public class AppStatusTracker implements Application.ActivityLifecycleCallbacks {
	private static final long MAX_INTERVAL = 5 * 60 * 1000;
	private static AppStatusTracker tracker;
	private int mAppStatus = ConstantValues.STATUS_ONLINE;
	private Application mApplication;
	private boolean isForground;
	private DameonReceiver mDameonReceiver;
	private boolean isScreenOff;
	private long timestamp;
	private int activeCount;


	private AppStatusTracker(Application application) {
		mApplication = application;
		mApplication.registerActivityLifecycleCallbacks(this);
	}

	public static void init(Application application) {
		tracker = new AppStatusTracker(application);
	}

	public static AppStatusTracker getInstance() {
		return tracker;
	}

	public void setAppStatus(int status) {
		this.mAppStatus = status;
		if (status == ConstantValues.STATUS_ONLINE) {
			if (mDameonReceiver == null) {
				IntentFilter filter = new IntentFilter();
				filter.addAction(Intent.ACTION_SCREEN_OFF);
				mDameonReceiver = new DameonReceiver();
				mApplication.registerReceiver(mDameonReceiver, filter);
			}
		}else if (mDameonReceiver != null) {
			mApplication.unregisterReceiver(mDameonReceiver);
			mDameonReceiver = null;
		}
	}

	public boolean isForground() {
		return isForground;
	}

	public int getAppStatus() {
		return mAppStatus;
	}

	public void onScreenOff(boolean isScreenOff) {
		this.isScreenOff = isScreenOff;
	}

	public boolean checkIfShowGesture() {
		if (mAppStatus == ConstantValues.STATUS_ONLINE) {
			if (isScreenOff) {
				return true;
			}
			if (timestamp != 0l && System.currentTimeMillis() - timestamp > MAX_INTERVAL) {
				return true;
			}
		}
		return false;
	}


	@Override
	public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

	}

	@Override
	public void onActivityStarted(Activity activity) {
		activeCount++;
	}

	@Override
	public void onActivityResumed(Activity activity) {
		isForground = true;
		timestamp = 0l;
		isScreenOff = false;
	}

	@Override
	public void onActivityPaused(Activity activity) {

	}

	@Override
	public void onActivityStopped(Activity activity) {
		activeCount--;
		if (activeCount == 0) {
			isForground = false;
			timestamp = System.currentTimeMillis();
		}
	}

	@Override
	public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

	}

	@Override
	public void onActivityDestroyed(Activity activity) {

	}

	private class DameonReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (Intent.ACTION_SCREEN_OFF.equals(action)) {
				AppStatusTracker.getInstance().onScreenOff(true);
			}
		}
	}
}
