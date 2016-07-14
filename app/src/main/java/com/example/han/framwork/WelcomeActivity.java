package com.example.han.framwork;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.han.framwork.constant.ConstantValues;
import com.example.han.framwork.core.AppStatusTracker;
import com.example.han.framwork.core.BaseActivity;

public class WelcomeActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		AppStatusTracker.getInstance().setAppStatus(ConstantValues.STATUS_OFFLINE);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void setupData() {
		setContentView(R.layout.activity_welcome, -1, MODE_NONE);
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

