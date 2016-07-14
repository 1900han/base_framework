package com.example.han.framwork.core;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.han.framwork.HomeActivity;
import com.example.han.framwork.R;
import com.example.han.framwork.constant.ConstantValues;
import com.example.han.framwork.utils.L;

/**
 * Created by han on 2016/7/5.
 */
public class BaseActivity extends AppCompatActivity implements View.OnClickListener, Toolbar.OnMenuItemClickListener {
	protected static final int MODE_BACK = 0;
	protected static final int MODE_NONE = 1;
	protected static final int MODE_HOME = 2;
	protected static final int MODE_DRAWER = 3;

	protected Toolbar toolbar;
	protected TextView toolbar_title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		switch (AppStatusTracker.getInstance().getAppStatus()) {
			case ConstantValues.STATUS_FORCE_KILLED:
				protectApp();
				break;
			case ConstantValues.STATUS_KICK_OUT:
				kickOut();
				break;
			case ConstantValues.STATUS_ONLINE:
			case ConstantValues.STATUS_LOGOUT:
			case ConstantValues.STATUS_OFFLINE:
				setupData();
				break;
		}
	}

	protected void kickOut() {
		// TODO: 2016/7/14  show dialog to comfirm
		Intent intent = new Intent(this, HomeActivity.class);
		intent.putExtra(ConstantValues.KEY_HOME_ACTION, ConstantValues.STATUS_KICK_OUT);
		startActivity(intent);
	}

	protected void protectApp() {
		Intent intent = new Intent(this, HomeActivity.class);
		intent.putExtra(ConstantValues.KEY_HOME_ACTION, ConstantValues.ACTION_RESTART_APP);
		startActivity(intent);
	}

	protected void setupData() {

	}

	@Override
	public void setContentView(int layoutResID) {
		setContentView(layoutResID, -1, -1, MODE_BACK);
	}

	public void setContentView(int layoutResID, int titleResId) {
		setContentView(layoutResID, titleResId, -1, MODE_BACK);
	}

	public void setContentView(int layoutResID, int titleResId, int mode) {
		setContentView(layoutResID, titleResId, -1, mode);
	}

	public void setContentView(int layoutResID, int titleResId, int menuId, int mode) {
		super.setContentView(layoutResID);
		setupToolbar(titleResId, menuId, mode);
	}

	protected void setupToolbar(int titleResId, int menuId, int mode) {
		if (mode != MODE_NONE) {
			toolbar = (Toolbar) findViewById(R.id.toolbar);
			toolbar.setTitle("");
			toolbar_title = (TextView) findViewById(R.id.toolbar_title);
			if (mode == MODE_BACK) {
				toolbar.setNavigationIcon(R.drawable.ic_toolbar_back);
			}
			toolbar.setNavigationOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					onNavigationBtnClicked();
				}
			});
			setupTitle(titleResId);
			setupMenu(menuId);
		}

	}

	protected void onNavigationBtnClicked() {
		finish();
	}

	protected void setupMenu(int menuId) {
		if (toolbar != null) {
			toolbar.getMenu().clear();
			if (menuId > 0) {
				toolbar.inflateMenu(menuId);
				toolbar.setOnMenuItemClickListener(this);
			}
		}
	}

	protected void setupTitle(int titleResId) {
		if (titleResId > 0 && toolbar_title != null) {
			toolbar_title.setText(titleResId);
		}
	}


	@Override
	public void onClick(View v) {

	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		return false;
	}

	@Override
	protected void onStart() {
		if (AppStatusTracker.getInstance().checkIfShowGesture()) {
			L.d("need to show gesture");
		}
		super.onStart();
	}
}
