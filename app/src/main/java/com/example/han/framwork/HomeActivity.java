package com.example.han.framwork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        setContentView(R.layout.activity_home);
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

        String action = getIntent().getStringExtra("action");
        if ("force_kill".equals(action)) {
            protectApp();
        }
    }
}
