package com.example.han.framwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.han.framwork.constant.ConstantValues;
import com.example.han.framwork.core.BaseActivity;
import com.example.han.framwork.core.CustomApplication;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void setupData() {
		setContentView(R.layout.activity_home, R.string.title_activity_home, R.menu.menu_home, MODE_HOME);
		Button mProfile = (Button) findViewById(R.id.btn_profile);
		mProfile.setOnClickListener(this);

		CustomApplication.mTestNullPointers = new ArrayList<>();
		CustomApplication.mTestNullPointers.add("profile");
	}

	@Override
	public void onClick(View v) {
		startActivity(new Intent(this, ProfileActivity.class));
	}

	@Override
	protected void protectApp() {
		startActivity(new Intent(this, WelcomeActivity.class));
		finish();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);

		int action = intent.getIntExtra(ConstantValues.KEY_HOME_ACTION, ConstantValues.ACTION_BACK_TO_HOME);
		switch (action) {
			case ConstantValues.ACTION_KICK_OUT:
				break;
			case ConstantValues.ACTION_LOGOUT:
				break;
			case ConstantValues.ACTION_BACK_TO_HOME:
				break;
			case ConstantValues.ACTION_RESTART_APP:
				protectApp();
				break;
		}
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		return super.onMenuItemClick(item);
	}
}
