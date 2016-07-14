package com.example.han.framwork;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.han.framwork.core.BaseActivity;
import com.example.han.framwork.core.CustomApplication;

public class WelcomeActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		CustomApplication.mAppStatus = 0;
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void setupData() {
		setContentView(R.layout.activity_welcome);
		mHandler.sendEmptyMessageDelayed(0, 1000);
	}

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
			finish();
		}
	};

	@Override
	protected void onPause() {
		super.onPause();
		mHandler.removeMessages(0);
	}
}

