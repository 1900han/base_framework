package com.example.han.framwork.core;

import android.app.Application;

import java.util.List;

/**
 * Created by han on 2016/7/13.
 */
public class CustomApplication extends Application {
	public static List<String> mTestNullPointers;

	@Override
	public void onCreate() {
		super.onCreate();
		AppStatusTracker.init(this);
	}
}
