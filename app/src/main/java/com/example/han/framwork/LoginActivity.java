package com.example.han.framwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.han.framwork.core.BaseActivity;

public class LoginActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	protected void setupData() {
		setContentView(R.layout.activity_login, R.string.title_activity_login);
		findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LoginActivity.this, HomeActivity.class));
				finish();
			}
		});
	}
}
