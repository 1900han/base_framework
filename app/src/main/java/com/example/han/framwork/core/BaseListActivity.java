package com.example.han.framwork.core;

import android.os.Bundle;

import com.example.han.framwork.R;

public class BaseListActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base_list);
	}
}
